package com.ProjectTrial1.Projectdemo1.account.role;


import java.util.List;

public interface RoleRepository  {

	Role findByRoleId(String id);
	Role save(Role role);
	List<Role> findAll();

}
