package org.example.account.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

	User findByUserId(String userId);
	
	User findByEmailId(String emailId);
	
	List<User> findAll();
}
