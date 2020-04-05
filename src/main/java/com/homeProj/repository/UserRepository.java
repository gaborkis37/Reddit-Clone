package com.homeProj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndActivationCode(String email, String activationCode);

	Optional<User> findByAlias(String alias);

}
