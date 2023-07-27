package com.ProjectTrial1.Projectdemo1.account.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  {

	User save(User user);

	User update (User user);
	List<User> findAll();
	User findByUserId(String userId);
	User findByEmailId(String emailId);

	User findById(int id);
}
