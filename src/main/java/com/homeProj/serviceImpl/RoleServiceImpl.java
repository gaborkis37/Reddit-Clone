package com.homeProj.serviceImpl;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Role;
import com.homeProj.repository.RoleRepository;
import com.homeProj.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

}
