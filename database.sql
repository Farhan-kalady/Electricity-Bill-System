CREATE DATABASE IF NOT EXISTS electricity_bill_system;

USE electricity_bill_system;

-- Admin credentials table
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- Insert a default admin for testing purposes
INSERT IGNORE INTO admin (username, password) VALUES ('admin', 'admin123');

-- Customers table
CREATE TABLE IF NOT EXISTS customers (
    cid VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Bills table
CREATE TABLE IF NOT EXISTS bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    cid VARCHAR(20),
    prev_reading INT NOT NULL,
    curr_reading INT NOT NULL,
    units INT NOT NULL,
    amount DOUBLE NOT NULL,
    bill_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date DATE,
    payment_status VARCHAR(20) DEFAULT 'Unpaid',
    FOREIGN KEY (cid) REFERENCES customers(cid) ON DELETE CASCADE
);
