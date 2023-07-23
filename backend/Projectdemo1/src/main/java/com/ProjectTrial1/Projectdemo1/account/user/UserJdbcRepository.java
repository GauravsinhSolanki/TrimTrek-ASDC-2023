package com.ProjectTrial1.Projectdemo1.account.user;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{


    @Override
    public User save(User user) {
        String query = "INSERT INTO users (user_id, user_name, user_password, gender, dob, phone_no, alt_phone_no, email_id, speciality, is_active, created_by, created_on, updated_by, updated_on) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassWord());
            statement.setString(4, user.getGender());
            statement.setDate(5, user.getDob() != null ? Date.valueOf(user.getDob()) : null);
            statement.setString(6, user.getPhoneNo());
            statement.setString(7, user.getAltPhoneNo());
            statement.setString(8, user.getEmailId());
            statement.setString(9, user.getSpeciality());
            statement.setBoolean(10, user.isActive());
            statement.setString(11, user.getCreatedBy());
            statement.setTimestamp(12, user.getCreatedOn() != null ? Timestamp.valueOf(user.getCreatedOn()) : null);
            statement.setString(13, user.getUpdatedBy());
            statement.setTimestamp(14, user.getUpdatedOn() != null ? Timestamp.valueOf(user.getUpdatedOn()) : null);;

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    user.setId(id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = resultSetToUser(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return users;
    }

    @Override
    public User findByUserId(String userId) {
        User user = null;

        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = resultSetToUser(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return user;
    }

    @Override
    public User findByEmailId(String emailId) {
        User user = null;

        String query = "SELECT * FROM users WHERE email_id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, emailId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = resultSetToUser(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return user;
    }

    private User resultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserId(resultSet.getString("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserPassWord(resultSet.getString("user_password"));
        user.setGender(resultSet.getString("gender"));
        user.setDob(resultSet.getDate("dob") != null ? resultSet.getDate("dob").toLocalDate() : null);
        user.setPhoneNo(resultSet.getString("phone_no"));
        user.setAltPhoneNo(resultSet.getString("alt_phone_no"));
        user.setEmailId(resultSet.getString("email_id"));
        user.setSpeciality(resultSet.getString("speciality"));
        user.setActive(resultSet.getBoolean("is_active"));
        user.setCreatedBy(resultSet.getString("created_by"));
        user.setCreatedOn(resultSet.getTimestamp("created_on") != null ? resultSet.getTimestamp("created_on").toLocalDateTime() : null);
        user.setUpdatedBy(resultSet.getString("updated_by"));
        user.setUpdatedOn(resultSet.getTimestamp("updated_on") != null ? resultSet.getTimestamp("updated_on").toLocalDateTime() : null);
        return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET speciality=? WHERE user_id=?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {


            statement.setString(1, user.getSpeciality());

            statement.setString(2, user.getUserId()); // Set the user_id for the WHERE clause

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return user;
    }
}
