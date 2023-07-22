package org.example.account.userrole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	List<UserRole> findByUserId(int id);
}
