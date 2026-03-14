package com.billingsystem;

import javax.swing.*;
import java.sql.*;

public class BillHistory extends JFrame {

    JTable table;
    String[] columnNames = {"Bill ID", "Customer ID", "Prev Reading", "Curr Reading", "Units", "Amount (₹)", "Date"};
    String[][] data = new String[100][7];

    public BillHistory() {
        super("Bill History");
        setSize(800, 500);
        setLocation(200, 150);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM bills";
            ResultSet rs = c.s.executeQuery(query);
            
            int i = 0;
            while(rs.next() && i < 100) {
                data[i][0] = String.valueOf(rs.getInt("bill_id"));
                data[i][1] = rs.getString("cid");
                data[i][2] = String.valueOf(rs.getInt("prev_reading"));
                data[i][3] = String.valueOf(rs.getInt("curr_reading"));
                data[i][4] = String.valueOf(rs.getInt("units"));
                data[i][5] = String.valueOf(rs.getDouble("amount"));
                data[i][6] = rs.getString("bill_date");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        add(sp, "Center");
        
        // Add a print button at the bottom
        JButton btnPrint = new JButton("Print History");
        btnPrint.addActionListener(e -> {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(btnPrint, "South");
    }

    public static void main(String[] args) {
        new BillHistory().setVisible(true);
    }
}
