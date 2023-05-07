package com.ca.security.roles.demo78;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/goods";
        String username = "zylius";
        String password = "seniukas";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Pavyko prisijungti prie duomenų bazės!");

            // Example query
            String query = "SELECT * FROM users";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    // Process the retrieved data
                    // For example, retrieve values from the resultSet
                    String column1Value = resultSet.getString("column1");
                    int column2Value = resultSet.getInt("column2");
                    // ...
                }
            } catch (SQLException e) {
                System.out.println("Klaida vykdant užklausą: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Nepavyko prisijungti prie duomenų bazės.");
            e.printStackTrace();
        }
    }
}


