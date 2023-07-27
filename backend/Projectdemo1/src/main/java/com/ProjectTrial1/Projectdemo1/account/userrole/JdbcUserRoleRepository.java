package com.ProjectTrial1.Projectdemo1.account.userrole;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcUserRoleRepository implements UserRoleRepository{
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user_role";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM user_role WHERE id=?";
    private static final String SELECT_BY_USER_ID_QUERY = "SELECT * FROM user_role WHERE user_id=?";
    private static final String INSERT_QUERY = "INSERT INTO user_role (user_id, role_id, is_active, created_by, created_on, updated_by, updated_on) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<UserRole> findAll() {
        List<UserRole> userRoles = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                userRoles.add(mapRowToUserRole(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    @Override
    public UserRole findById(int id) {
        UserRole userRole = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userRole = mapRowToUserRole(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }

    @Override
    public List<UserRole> findByUserId(int userId) {
        List<UserRole> userRoles = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userRoles.add(mapRowToUserRole(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    @Override
    public List<UserRole> saveAll(List<UserRole> userRoles) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            for (UserRole userRole : userRoles) {
                statement.setInt(1, userRole.getUserId());
                statement.setInt(2, userRole.getRoleId());
                statement.setBoolean(3, userRole.isActive());
                statement.setString(4, userRole.getCreatedBy());
                statement.setObject(5, userRole.getCreatedOn());
                statement.setString(6, userRole.getUpdatedBy());
                statement.setObject(7, userRole.getUpdatedOn());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    private UserRole mapRowToUserRole(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int roleId = resultSet.getInt("role_id");
        boolean isActive = resultSet.getBoolean("is_active");
        String createdBy = resultSet.getString("created_by");
        LocalDateTime createdOn = resultSet.getTimestamp("created_on").toLocalDateTime();
        String updatedBy = resultSet.getString("updated_by");
//        LocalDateTime updatedOn = resultSet.getTimestamp("updated_on").toLocalDateTime();

        return new UserRole(id, userId, roleId, isActive, createdBy, createdOn, updatedBy, LocalDateTime.now());
    }
}
