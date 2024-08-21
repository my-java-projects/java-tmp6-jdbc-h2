package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class MySQLConnectionWithConfig {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load database properties from config file
            Properties props = new Properties();
            InputStream input = MySQLConnectionWithConfig.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            props.load(input);

            // Get properties
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Establishing a connection
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
                String sql = "insert into users (username, password) values (?, ?)";

                var stmt = connection.prepareStatement(sql);
                stmt.setObject(1, "username-test");
                stmt.setObject(2, "p@ssw@rd");

                int result = stmt.executeUpdate();

                if(result > 0)
                    System.out.println("Update the database");
            }

        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
