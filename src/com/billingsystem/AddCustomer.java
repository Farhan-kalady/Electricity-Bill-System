package com.billingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {
    
    JLabel l1, l2, l3, l4, l5;
    JTextField tfCid, tfName, tfAddress, tfPhone;
    JButton btnSubmit, btnCancel;

    public AddCustomer() {
        super("Add New Customer");
        setLocation(350, 200);
        setSize(700, 400);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(240, 255, 240));

        l1 = new JLabel("Add New Customer");
        l1.setBounds(250, 20, 200, 30);
        l1.setFont(new Font("Tahoma", Font.BOLD, 18));
        p.add(l1);

        l2 = new JLabel("Customer ID");
        l2.setBounds(100, 80, 100, 20);
        p.add(l2);

        tfCid = new JTextField();
        tfCid.setBounds(250, 80, 200, 20);
        p.add(tfCid);

        l3 = new JLabel("Name");
        l3.setBounds(100, 120, 100, 20);
        p.add(l3);

        tfName = new JTextField();
        tfName.setBounds(250, 120, 200, 20);
        p.add(tfName);

        l4 = new JLabel("Address");
        l4.setBounds(100, 160, 100, 20);
        p.add(l4);

        tfAddress = new JTextField();
        tfAddress.setBounds(250, 160, 200, 20);
        p.add(tfAddress);

        l5 = new JLabel("Phone No");
        l5.setBounds(100, 200, 100, 20);
        p.add(l5);

        tfPhone = new JTextField();
        tfPhone.setBounds(250, 200, 200, 20);
        p.add(tfPhone);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 280, 100, 30);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        p.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(300, 280, 100, 30);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        p.add(btnCancel);

        setLayout(new BorderLayout());
        add(p, "Center");

        // Assuming image might not be present, we handle gracefully or let layout ignore it
        // ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        // Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        // ImageIcon i3 = new ImageIcon(i2);
        // JLabel lImage = new JLabel(i3);
        // add(lImage, "West");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSubmit) {
            String cid = tfCid.getText();
            String name = tfName.getText();
            String address = tfAddress.getText();
            String phone = tfPhone.getText();

            if (cid.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            String query = "INSERT INTO customers VALUES('" + cid + "', '" + name + "', '" + address + "', '" + phone + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == btnCancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddCustomer().setVisible(true);
    }
}
