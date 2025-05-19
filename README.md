# MiniSahibinden Java + MySQL Project

This project is a simplified version of Sahibinden.com built using **Java**, **Swing UI**, and **MySQL**. It allows users to view cars, manage listings, and store favorites using a relational database.

---

## 🚀 How to Set Up

### 1. ✅ Clone the Repository
```bash
git clone https://github.com/AcarBasaran/miniSahibinden
cd miniSahibinden
```

### 2. ✅ Import SQL Database
Open `sql/miniSahibindenDump.sql` using **MySQL Workbench** and click the ⚡ (Execute) button.

This will:
- Create the `miniSahibinden` database
- Build all tables and relationships
- Populate with real data (Users, Cars, Models, etc.)

### 3. ✅ Run the Java Project
This project is a plain Java project (not Maven or Gradle).

> **Important:** You must manually add the MySQL JDBC driver `.jar` file.
>

#### 👉 How to Add JDBC Driver in IntelliJ
1. [Download MySQL Connector/J here](https://dev.mysql.com/downloads/connector/j/)
2. Platform Independent secmelisiniz
2. Extract the ZIP
3. Go to **File > Project Structure > Libraries**
4. Click `+` and add the `.jar` file

### 4. ✅ Update Your Credentials
In `DBConnection.java`, update the MySQL username and password:
```java
private static final String USER = "root"; // change if needed
private static final String PASSWORD = "your_password";
```

---

## 📂 Project Structure
```
miniSahibinden/
├── sql/
│   └── miniSahibindenDump.sql     # Full schema and data
├── src/miniSahibinden/

