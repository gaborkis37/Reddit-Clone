package com.homeProj.service;

import java.util.Optional;

import com.homeProj.domain.User;

public interface UsersService {

	User register(User user);

	User save(User user);

	void sendActivationEmail(User user);

	void sendWelcomeEmail(User user);

	Optional<User> findByEmailAndActivationCode(String email, String activationCode);

	Optional<User> findByEmail(String email);

	Optional<User> findByAlias(String alias);

}