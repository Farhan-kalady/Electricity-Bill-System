# Electricity Bill Management System (Java Swing + MySQL)

A Java Object-Oriented application for managing electricity bills. Features a GUI built with Java Swing and uses MySQL as a database backend to maintain admin accounts, calculate tariff-based billing, and track customer bill history.

## Project Structure
- `src/com/billingsystem` - Contains all Java source files.
- `database.sql` - Contains the MySQL schema definition and sample admin data.

## Setup Instructions

### 1. Database Setup
1. Ensure you have MySQL Server installed and running.
2. Open your MySQL command-line tool or a GUI client (like MySQL Workbench, phpMyAdmin, etc.)
3. Execute the SQL statements from the `database.sql` file. This creates the database `electricity_bill_system` along with the `admin`, `customers`, and `bills` tables. Also inserts a default admin account (username: `admin`, password: `admin123`).

### 2. Running the Java Application
1. Download `mysql-connector-j-8.0.33.jar` (or latest) from the official MySQL website.
2. Put the `.jar` inside your project directory (or any known path).
3. Open `src/com/billingsystem/Conn.java` and verify:
   ```java
   c = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_system", "root", "");
   ```
   *Change `"root"` to your MySQL username and `""` to your password if it's not empty.*
4. Open your Command Prompt (or Terminal) and navigate to the project directory (`d:\SOFINS\Github\electricity java project`):
   ```bash
   cd src
   javac -d . com/billingsystem/*.java
   ```
5. Run the Application by launching the `Login` class, and bind the MySQL library to the classpath (`-cp`):
   ```bash
   # Windows
   java -cp ".;../mysql-connector-j-8.0.33.jar" com.billingsystem.Login
   
   # Linux/Mac
   java -cp ".:../mysql-connector-j-8.0.33.jar" com.billingsystem.Login
   ```

### 3. Usage Flow
- **Login**: Use `admin` / `admin123`.
- **Add Customer**: Add a user (e.g., CID "C01", Name "John Doe").
- **Calculate Bill**: Pick "C01", add readings like `100` and `250`.
- **Generate Bill**: Search for "C01" to beautifully layout the total amount to pay based on tariff slabs.
- **Bill History**: View all calculated bills across all users. 
