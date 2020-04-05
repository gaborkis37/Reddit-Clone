package com.homeProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
