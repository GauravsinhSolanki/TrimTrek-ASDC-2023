package com.ProjectTrial1.Projectdemo1.UserAuthentication;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserAuthenticationJdbcRepository implements UserAuthenticationRepository{

    @Override
    public boolean setUserTokenDB(String userEmail) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        UserAuthentication userAuthentication = new UserAuthentication(userEmail);
        int rowsInserted =0;

        try {
            connection = ConnectionManager.getConnection();
            statement = connection.createStatement();
            String insertQuery = "INSERT INTO user_tokens (email, token) VALUES (?,?);\n";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userAuthentication.getToken());
            rowsInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }
        if (rowsInserted > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getUserTokenDB(String userEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String token = null;
        boolean isvalid;
        try {
            connection = ConnectionManager.getConnection();
            String selectQuery = "SELECT token FROM user_tokens WHERE email = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userEmail);
            resultSet = preparedStatement.executeQuery();
            if(resultSet == null){
                return false;
            }else{
                if (resultSet.next()) {
                    token = resultSet.getString("token");
                }
                UserAuthentication userAuthentication = new UserAuthentication(userEmail,token);
                isvalid = userAuthentication.isTokenValid(userAuthentication.getToken());
                return isvalid;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        return false ;
    }
    @Override

    public boolean deleteUserTokenDB(String userEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();
            String deleteQuery = "DELETE FROM user_tokens WHERE email = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, userEmail);

            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("rowsDeLETED"+ rowsDeleted);
            if(rowsDeleted > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
        return false;
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
