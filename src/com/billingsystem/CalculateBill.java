package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculateBill extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, lbUnitCost;
    JTextField tfCid, tfPrevReading, tfCurrReading;
    JButton btnSubmit, btnCancel;

    public CalculateBill() {
        super("Calculate Electricity Bill");
        setBounds(350, 200, 500, 400);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(255, 250, 205));

        l1 = new JLabel("Calculate Electricity Bill");
        l1.setBounds(120, 10, 300, 30);
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        p.add(l1);

        l2 = new JLabel("Customer ID");
        l2.setBounds(60, 80, 150, 20);
        p.add(l2);

        tfCid = new JTextField();
        tfCid.setBounds(250, 80, 150, 20);
        p.add(tfCid);

        l3 = new JLabel("Previous Reading");
        l3.setBounds(60, 130, 150, 20);
        p.add(l3);

        tfPrevReading = new JTextField();
        tfPrevReading.setBounds(250, 130, 150, 20);
        p.add(tfPrevReading);

        l4 = new JLabel("Current Reading");
        l4.setBounds(60, 180, 150, 20);
        p.add(l4);

        tfCurrReading = new JTextField();
        tfCurrReading.setBounds(250, 180, 150, 20);
        p.add(tfCurrReading);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(100, 260, 100, 30);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        p.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 260, 100, 30);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        p.add(btnCancel);

        setLayout(new BorderLayout());
        add(p, "Center");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSubmit) {
            String cid = tfCid.getText();
            String prevReadStr = tfPrevReading.getText();
            String currReadStr = tfCurrReading.getText();

            if (cid.isEmpty() || prevReadStr.isEmpty() || currReadStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
                return;
            }

            try {
                int prevRead = Integer.parseInt(prevReadStr);
                int currRead = Integer.parseInt(currReadStr);
                
                if (currRead < prevRead) {
                    JOptionPane.showMessageDialog(null, "Current Reading cannot be less than Previous Reading.");
                    return;
                }

                int units = currRead - prevRead;
                double billAmount = 0;

                // Tariff Calculation
                // First 100 units = ₹1.5 per unit
                // Next 100 units = ₹2.5 per unit
                // Above 200 units = ₹4 per unit
                
                if (units <= 100) {
                    billAmount = units * 1.5;
                } else if (units <= 200) {
                    billAmount = (100 * 1.5) + ((units - 100) * 2.5);
                } else {
                    billAmount = (100 * 1.5) + (100 * 2.5) + ((units - 200) * 4.0);
                }

                String query = "INSERT INTO bills (cid, prev_reading, curr_reading, units, amount) VALUES ('" + cid + "', " + prevRead + ", " + currRead + ", " + units + ", " + billAmount + ")";

                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Bill Saved Successfully\nUnits: " + units + "\nTotal Amount: ₹" + billAmount);
                setVisible(false);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Readings must be integers.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Submitting Bill. Checking Customer ID exists.");
            }
        } else if (ae.getSource() == btnCancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill().setVisible(true);
    }
}
