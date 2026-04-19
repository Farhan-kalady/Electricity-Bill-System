USE electricity_bill_system;

-- Add due_date and payment_status columns to the bills table
ALTER TABLE bills 
ADD COLUMN due_date DATE,
ADD COLUMN payment_status VARCHAR(20) DEFAULT 'Unpaid';

-- To apply default logic to existing old bills, you can optionally run:
-- UPDATE bills SET due_date = DATE_ADD(DATE(bill_date), INTERVAL 15 DAY), payment_status = 'Unpaid' WHERE due_date IS NULL;
