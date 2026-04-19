package com.billingsystem;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class BillHistory extends JFrame {

    JTable table;
    String[] columnNames = {"Bill ID", "Customer ID", "Prev Reading", "Curr Reading", "Units", "Amount (₹)", "Date", "Due Date", "Status"};
    String[][] data = new String[100][9];

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
                
                String dueDateStr = rs.getString("due_date");
                String paymentStatus = rs.getString("payment_status");
                
                if (dueDateStr != null) {
                    try {
                        LocalDate due = java.sql.Date.valueOf(dueDateStr).toLocalDate();
                        if (LocalDate.now().isAfter(due) && "Unpaid".equalsIgnoreCase(paymentStatus)) {
                            paymentStatus = "Overdue";
                        }
                    } catch (Exception ex) {
                        // ignore formatting exception
                    }
                } else {
                    dueDateStr = "N/A";
                    paymentStatus = "N/A";
                }
                
                data[i][7] = dueDateStr;
                data[i][8] = paymentStatus;
                
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        add(sp, "Center");
        
        JPanel btnPanel = new JPanel();
        
        JButton btnPrint = new JButton("Print History");
        btnPrint.addActionListener(e -> {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        JButton btnMarkPaid = new JButton("Mark as Paid");
        btnMarkPaid.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String billId = data[row][0];
                if (billId != null) {
                    try {
                        Conn c = new Conn();
                        c.s.executeUpdate("UPDATE bills SET payment_status = 'Paid' WHERE bill_id = " + billId);
                        JOptionPane.showMessageDialog(null, "Bill marked as Paid successfully!");
                        table.setValueAt("Paid", row, 8);
                        data[row][8] = "Paid";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error updating status.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a bill from the table first.");
            }
        });
        
        btnPanel.add(btnMarkPaid);
        btnPanel.add(btnPrint);
        
        add(btnPanel, "South");
    }

    public static void main(String[] args) {
        new BillHistory().setVisible(true);
    }
}
