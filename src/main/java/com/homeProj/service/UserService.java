package com.homeProj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.homeProj.domain.User;
import com.homeProj.repository.RoleRepository;
import com.homeProj.repository.UserRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	
	public User register(User user) {
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
}
