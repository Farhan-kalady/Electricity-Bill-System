package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JLabel l1, l2;
    JTextField tfUsername;
    JPasswordField pfPassword;
    JButton btnLogin, btnCancel;

    public Login() {
        super("Electricity Bill System - Login");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        l1 = new JLabel("Username:");
        l1.setBounds(300, 20, 100, 20);
        add(l1);

        l2 = new JLabel("Password:");
        l2.setBounds(300, 60, 100, 20);
        add(l2);

        tfUsername = new JTextField(15);
        tfUsername.setBounds(400, 20, 150, 20);
        add(tfUsername);

        pfPassword = new JPasswordField(15);
        pfPassword.setBounds(400, 60, 150, 20);
        add(pfPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(330, 160, 100, 20);
        btnLogin.addActionListener(this);
        add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(450, 160, 100, 20);
        btnCancel.addActionListener(this);
        add(btnCancel);

        // Add a simple image or just use space
        // We will just skip the image insertion if it's missing to avoid crashes and keep it simple
        // Instead we can use a basic label
        JLabel imageLabel = new JLabel("ELECTRICITY BILLING LOGIN", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        imageLabel.setBounds(0, 0, 250, 250);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(173, 216, 230)); // Light Blue
        add(imageLabel);

        setBounds(400, 300, 600, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLogin) {
            String username = tfUsername.getText();
            String password = new String(pfPassword.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both username and password!");
                return;
            }

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM admin WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    setVisible(false);
                    new Dashboard().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login!");
                    tfUsername.setText("");
                    pfPassword.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Connection Error! Ensure MySQL is running and Conn.java is configured.");
            }
        } else if (ae.getSource() == btnCancel) {
            setVisible(false);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
