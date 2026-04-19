package com.billingsystem;

public class ScratchUpdateDB {
    public static void main(String[] args) {
        try {
            Conn c = new Conn();
            try {
                c.s.executeUpdate("ALTER TABLE bills ADD COLUMN due_date DATE");
                System.out.println("Added due_date");
            } catch (Exception e) {}
            
            try {
                c.s.executeUpdate("ALTER TABLE bills ADD COLUMN payment_status VARCHAR(20) DEFAULT 'Unpaid'");
                System.out.println("Added payment_status");
            } catch (Exception e) {}
            
            // Apply logic to existing rows just in case
            try {
                c.s.executeUpdate("UPDATE bills SET due_date = DATE_ADD(DATE(bill_date), INTERVAL 15 DAY), payment_status = 'Unpaid' WHERE due_date IS NULL");
                System.out.println("Updated defaults for old rows.");
            } catch (Exception e) {}
            
            System.out.println("DB Update Finished");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
