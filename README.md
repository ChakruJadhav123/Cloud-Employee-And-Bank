# Cloud EMS + Banking (Java + Oracle + Maven)

## Steps to Run Locally
1. Install Oracle Database (19c or XE) and create user `chakru/chakru`
2. Execute `db.sql` in SQL*Plus or SQL Developer
3. Update `DBConnection.java` with your Oracle credentials if different
4. Build project:
   ```
   mvn clean package
   ```
5. Run application:
   ```
   java -jar target/CloudEMS-Banking-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Features
- Employee Management (Add, View)
- Banking Management (Accounts, Deposit, Withdraw, Transactions)

## Next Steps
- Jenkins pipeline (CI/CD) on AWS
- Optional: Move DB to AWS RDS
# Cloud-Employee-And-Bank
