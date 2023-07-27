package com.ProjectTrial1.Projectdemo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public ConnectionManager() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asdc_final_project", "root", "neelsql");
        } catch (ClassNotFoundException var1) {
            var1.printStackTrace();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return connection;
    }
}
