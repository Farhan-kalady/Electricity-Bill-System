package com.billingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    
    public Connection c;
    public Statement s;
    
    public Conn() {
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection (Update "root" and "" with your MySQL username and password if different)
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_system", "root", "5260");
            
            // Create the statement object
            s = c.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
