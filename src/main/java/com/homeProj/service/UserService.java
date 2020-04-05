package com.homeProj.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.homeProj.domain.User;
import com.homeProj.repository.UserRepository;

@Service
public class UserService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final RoleService roleService;
	private final MailService mailService;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleService roleService,
			MailService mailService) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.roleService = roleService;
		this.mailService = mailService;
	}

	public User register(User user) {
		String secret = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(secret);
		user.setConfirmPassword(secret);
		user.addRole(roleService.findByName("ROLE_USER"));
		user.setActivationCode(UUID.randomUUID().toString());
		save(user);
		sendActivationEmail(user);
		return user;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void sendActivationEmail(User user) {
		mailService.sendActivationEmail(user);
	}

	public void sendWelcomeEmail(User user) {
		mailService.sendWelcomeEmail(user);	
	}
}
