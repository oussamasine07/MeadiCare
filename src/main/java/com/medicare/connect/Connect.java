package com.medicare.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private String jdbcURL = "jdbc:mysql://localhost:3306/medicare?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Climbing0673!";

    protected Connection getConnection () {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("database connected");
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;

    }
}
