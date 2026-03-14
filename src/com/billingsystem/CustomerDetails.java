package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

// A simple display and search window for Customer Details
public class CustomerDetails extends JFrame implements ActionListener {

    JTable table;
    JButton search, print;
    JTextField searchField;

    public CustomerDetails() {
        super("Customer Details");
        setSize(800, 500);
        setLocation(200, 150);

        // We will build a simple JTable, though we aren't using rs2xml to keep dependencies low
        // So we'll fetch data and use a basic table model or simple JTextArea. Let's try JTable with columns
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        p1.add(new JLabel("Search By Customer ID: "));
        searchField = new JTextField(15);
        p1.add(searchField);

        search = new JButton("Search");
        search.addActionListener(this);
        p1.add(search);

        print = new JButton("Print");
        print.addActionListener(this);
        p1.add(print);

        add(p1, "North");

        // Table
        String[] columnNames = {"Customer ID", "Name", "Address", "Phone No"};
        String[][] data = new String[100][4]; // Support up to 100 for this simple academic project
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customers");
            int i = 0;
            while(rs.next() && i < 100) {
                data[i][0] = rs.getString("cid");
                data[i][1] = rs.getString("name");
                data[i][2] = rs.getString("address");
                data[i][3] = rs.getString("phone");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        add(sp, "Center");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String q = "SELECT * FROM customers WHERE cid = '" + searchField.getText() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(q);
                // Clear existing table and redraw or just show dialog for simplicity
                if(rs.next()) {
                    String info = "Customer ID: " + rs.getString("cid") + "\n" +
                                  "Name: " + rs.getString("name") + "\n" +
                                  "Address: " + rs.getString("address") + "\n" +
                                  "Phone: " + rs.getString("phone");
                    JOptionPane.showMessageDialog(null, info, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Customer Not Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CustomerDetails().setVisible(true);
    }
}
