package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    
    public Dashboard() {
        super("Electricity Bill System - Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full Screen
        
        // Background color or welcome text
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("Welcome to Electricity Bill Management System", SwingConstants.CENTER);
        l1.setFont(new Font("Tahoma", Font.BOLD, 36));
        l1.setForeground(Color.BLUE);
        panel.add(l1, BorderLayout.CENTER);
        add(panel);

        // Menu Bar
        JMenuBar mb = new JMenuBar();
        
        // Master Menu
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);
        
        JMenuItem m1 = new JMenuItem("New Customer");
        m1.setBackground(Color.WHITE);
        m1.addActionListener(this);
        master.add(m1);
        
        JMenuItem m2 = new JMenuItem("Customer Details");
        m2.setBackground(Color.WHITE);
        m2.addActionListener(this);
        master.add(m2);

        // User / Billing Menu
        JMenu user = new JMenu("Billing");
        user.setForeground(Color.RED);
        
        JMenuItem u1 = new JMenuItem("Calculate Bill");
        u1.setBackground(Color.WHITE);
        u1.addActionListener(this);
        user.add(u1);
        
        JMenuItem u2 = new JMenuItem("Generate Bill");
        u2.setBackground(Color.WHITE);
        u2.addActionListener(this);
        user.add(u2);
        
        // Report Menu
        JMenu report = new JMenu("Report");
        report.setForeground(Color.BLUE);
        
        JMenuItem r1 = new JMenuItem("Bill History");
        r1.setBackground(Color.WHITE);
        r1.addActionListener(this);
        report.add(r1);
        
        // Exit Menu
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.RED);
        
        JMenuItem ut1 = new JMenuItem("Exit");
        ut1.setBackground(Color.WHITE);
        ut1.addActionListener(this);
        utility.add(ut1);

        mb.add(master);
        mb.add(user);
        mb.add(report);
        mb.add(utility);
        
        setJMenuBar(mb);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new AddCustomer().setVisible(true);
        } else if (msg.equals("Customer Details")) {
            // new CustomerDetails().setVisible(true); // Optional tracking UI
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill().setVisible(true);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill().setVisible(true);
        } else if (msg.equals("Bill History")) {
            new BillHistory().setVisible(true);
        } else if (msg.equals("Exit")) {
            setVisible(false);
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}
