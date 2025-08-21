package com.solvd.project.dao;

import java.sql.Connection;
import com.solvd.project.utils.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gdbcimpl {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String args[]) throws ClassNotFoundException {
        Connection conn = null;

        try {
            // Register the JDBC driver
            Class.forName(DB_DRIVER);

            // Open the connection
            conn = ConnectionPool.getConnection();

            if (conn != null) {
                System.out.println("Successfully connected.");
            } else {
                System.out.println("Failed to connect.");
            }

            Statement stmt = conn.createStatement();

            // Example query: list all tables in the database
            String sql = "SELECT PH.Name\r\n" + //
                    "FROM PolicyHolders PH\r\n";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("📋 Tables in InsuranceCompany:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
