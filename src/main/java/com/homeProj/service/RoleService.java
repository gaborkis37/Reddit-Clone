package com.homeProj.service;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Role;
import com.homeProj.repository.RoleRepository;

@Service
public class RoleService {

	RoleRepository roleRepo;

	public RoleService(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

}
