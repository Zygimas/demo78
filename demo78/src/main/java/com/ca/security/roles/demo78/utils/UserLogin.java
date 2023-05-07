package com.ca.security.roles.demo78.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLogin {

    public List<LoginField> getLoginFieldsFromDatabase() {
        List<LoginField> loginFields = new ArrayList<>();

        // Set the database connection information
        String url = "jdbc:mysql://localhost:3307/goods";
        String username = "zylius";
        String password = "seniukas";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create an SQL query to retrieve login fields from the usersLogin table
            String sql = "SELECT username1, encrypted_password FROM usersLogin";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                // Iterate over the result set and create LoginField objects
                while (resultSet.next()) {
                    String username1 = resultSet.getString("username1");
                    String encrypted_password = resultSet.getString("encrypted_password");

                    // Create a LoginField object and add it to the list
                    LoginField loginField = new LoginField(username1, encrypted_password);
                    loginFields.add(loginField);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginFields;
    }
}
