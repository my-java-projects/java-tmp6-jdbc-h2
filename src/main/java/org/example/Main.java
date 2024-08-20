package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            String jdbcUrl = "jdbc:h2:~/test";
            String username = "admin";
            String password = "admin";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username , password);
                System.out.println("connected to h2 database");
                /*String sql = "INSERT INTO students (name) VALUES('negar')";
                Statement statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("Rows affected: " + rowsAffected);
                }*/

                String sql = "SELECT * FROM students";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                int count = 0;
                while (resultSet.next()) {
                    count++;
                    int ID = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    System.out.println("stundent "+count+" : "  + ID +" , "+ name);
                }

                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }
}