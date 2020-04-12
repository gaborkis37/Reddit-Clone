package com.homeProj.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.homeProj.domain.User;
import com.homeProj.repository.UserRepository;
import com.homeProj.service.EmailService;
import com.homeProj.service.RoleService;
import com.homeProj.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final RoleService roleService;
	private final EmailService mailService;

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleService roleService,
			EmailService mailService) {
		super();
		this.userRepository = userRepository;
		this.encoder = new BCryptPasswordEncoder();
		this.roleService = roleService;
		this.mailService = mailService;
	}

	@Override
	public User register(User user) {
		String secret = encoder.encode(user.getPassword());
		user.setPassword(secret);
		user.setConfirmPassword(secret);
		user.addRole(roleService.findByName("ROLE_USER"));
		user.setActivationCode(UUID.randomUUID().toString());
		user.setCreated(LocalDateTime.now());
		save(user);
		sendActivationEmail(user);
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void sendActivationEmail(User user) {
		mailService.sendActivationEmail(user);
	}

	@Override
	public void sendWelcomeEmail(User user) {
		mailService.sendWelcomeEmail(user);
	}

	@Override
	public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
		return userRepository.findByEmailAndActivationCode(email, activationCode);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByAlias(String alias) {
		return userRepository.findByAlias(alias);
	}
	
	@Override
	public void storeProfilePicture(User user, MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				LOGGER.info("Sorry! Filename contains invalid path sequence " + fileName);
			}

			user.setProfilePicture(file.getBytes());
			save(user);
		} catch (IOException ex) {
			LOGGER.info("Could not store file " + fileName + ". Please try again!");

		}
	}
	
	@Override
	public void deleteProfilePicture(User user) {
		user.setProfilePicture(null);
		save(user);
	}
}
