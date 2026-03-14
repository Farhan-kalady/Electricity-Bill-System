package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class GenerateBill extends JFrame implements ActionListener {
    
    JTextArea t1;
    JButton btnPrint, btnSearch;
    JTextField tfCid;
    
    public GenerateBill() {
        setBounds(400, 100, 500, 600);
        setLayout(new BorderLayout());
        
        JPanel p = new JPanel();
        
        JLabel l1 = new JLabel("Customer ID:");
        p.add(l1);
        
        tfCid = new JTextField(15);
        p.add(tfCid);
        
        btnSearch = new JButton("Generate");
        btnSearch.addActionListener(this);
        p.add(btnSearch);
        
        add(p, "North");
        
        t1 = new JTextArea(50, 15);
        t1.setFont(new Font("Monospaced", Font.PLAIN, 14));
        t1.setEditable(false);
        JScrollPane jsp = new JScrollPane(t1);
        add(jsp, "Center");
        
        btnPrint = new JButton("Print");
        btnPrint.addActionListener(this);
        add(btnPrint, "South");
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSearch) {
            String cid = tfCid.getText();
            
            t1.setText("\n    ELECTRICITY BILLING SYSTEM\n");
            t1.append("    -------------------------------\n\n");
            
            try {
                Conn c = new Conn();
                // Get customer info
                ResultSet rs = c.s.executeQuery("SELECT * FROM customers WHERE cid = '"+cid+"'");
                if(rs.next()){
                    t1.append(" Customer ID     : " + rs.getString("cid") + "\n");
                    t1.append(" Customer Name   : " + rs.getString("name") + "\n");
                    t1.append(" Address         : " + rs.getString("address") + "\n");
                    t1.append(" Phone Number    : " + rs.getString("phone") + "\n");
                    t1.append(" ---------------------------------\n");
                } else {
                    t1.append(" Customer not found.\n");
                    return;
                }
                
                // Get most recent bill
                ResultSet rs2 = c.s.executeQuery("SELECT * FROM bills WHERE cid = '"+cid+"' ORDER BY bill_id DESC LIMIT 1");
                if(rs2.next()){
                    t1.append(" Previous Reading : " + rs2.getInt("prev_reading") + "\n");
                    t1.append(" Current Reading  : " + rs2.getInt("curr_reading") + "\n");
                    t1.append(" Units Consumed   : " + rs2.getInt("units") + "\n");
                    t1.append(" ---------------------------------\n");
                    t1.append(" Tariff Rates:\n");
                    t1.append("  First 100 units = ₹1.5/u\n");
                    t1.append("  Next 100 units  = ₹2.5/u\n");
                    t1.append("  Above 200 units = ₹4/u\n");
                    t1.append(" ---------------------------------\n");
                    t1.append(" TOTAL PAYABLE    : ₹ " + rs2.getDouble("amount") + "\n");
                    t1.append(" BILL DATE        : " + rs2.getString("bill_date") + "\n");
                } else {
                    t1.append(" No bills found for this customer.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnPrint) {
            try {
                t1.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new GenerateBill().setVisible(true);
    }
}
