package com.homeProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
