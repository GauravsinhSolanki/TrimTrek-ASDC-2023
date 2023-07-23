package com.ProjectTrial1.Projectdemo1.account.role;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleJdbcRepository implements RoleRepository{
    @Override
    public Role findByRoleId(String id) {
        Role role = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM role WHERE role_id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    role = new Role();
                    role.setId(resultSet.getInt("id"));
                    role.setRoleId(resultSet.getString("role_id"));
                    role.setRoleName(resultSet.getString("role_name"));
                    role.setRoleDescription(resultSet.getString("role_description"));
                    role.setActive(resultSet.getBoolean("is_active"));
                    role.setCreatedBy(resultSet.getString("created_by"));
                    role.setCreatedOn(resultSet.getObject("created_on", LocalDateTime.class));
                    role.setUpdatedBy(resultSet.getString("updated_by"));
                    role.setUpdatedOn(resultSet.getObject("updated_on", LocalDateTime.class));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role save(Role role) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO role (role_id, role_name, role_description, is_active, created_by, created_on) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getRoleId());
            statement.setString(2, role.getRoleName());
            statement.setString(3, role.getRoleDescription());
            statement.setBoolean(4, role.isActive());
            statement.setString(5, role.getCreatedBy());
            statement.setObject(6, role.getCreatedOn());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating role failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating role failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM role");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleId(resultSet.getString("role_id"));
                role.setRoleName(resultSet.getString("role_name"));
                role.setRoleDescription(resultSet.getString("role_description"));
                role.setActive(resultSet.getBoolean("is_active"));
                role.setCreatedBy(resultSet.getString("created_by"));
                role.setCreatedOn(resultSet.getObject("created_on", LocalDateTime.class));
                role.setUpdatedBy(resultSet.getString("updated_by"));
                role.setUpdatedOn(resultSet.getObject("updated_on", LocalDateTime.class));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
