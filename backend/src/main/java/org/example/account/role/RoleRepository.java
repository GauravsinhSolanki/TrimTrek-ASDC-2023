package org.example.account.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRoleId(String id);
}
