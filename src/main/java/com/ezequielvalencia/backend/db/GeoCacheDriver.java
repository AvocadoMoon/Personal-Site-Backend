package com.ezequielvalencia.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GeoCacheDriver {

    // Database credentials
    private static final String URL = ""; // Change to your database URL
    private static final String USER = "myuser"; // Change to your username
    private static final String PASSWORD = "mypassword"; // Change to your password

//    public static void main(String[] args) {
//        String textToInsert = "Hello, PostgreSQL!";
//        insertText(textToInsert);
//    }

//    public static void insertText(String text) {
//        String sql = "INSERT INTO my_table (my_column) VALUES (?)"; // Change table & column names
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, text);
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted > 0) {
//                System.out.println("Text inserted successfully!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
