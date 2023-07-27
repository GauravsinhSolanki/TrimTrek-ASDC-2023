package com.ProjectTrial1.Projectdemo1.account.userrole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository  {

	List<UserRole> findAll();

	UserRole findById(int id);

	List<UserRole> findByUserId(int userId);

	List<UserRole> saveAll(List<UserRole> userRoles);
}
