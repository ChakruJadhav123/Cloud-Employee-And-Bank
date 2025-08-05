package com.project.dao;

import java.sql.*;
import java.util.Scanner;
import com.project.utils.DBConnection;

public class BankingDAO {

    public void handleBanking(Scanner sc) {
        while (true) {
            System.out.println("\nBanking Module:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transactions");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 -> createAccount(sc);
                case 2 -> deposit(sc);
                case 3 -> withdraw(sc);
                case 4 -> checkBalance(sc);
                case 5 -> viewTransactions(sc);
                case 6 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void createAccount(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) { System.out.println("DB Connection failed."); return; }
        try {
            System.out.print("Enter Account Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO accounts(holder_name, balance) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, balance);
            ps.executeUpdate();
            System.out.println("Account created successfully.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deposit(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) { System.out.println("DB Connection failed."); return; }
        try {
            System.out.print("Enter Account ID: ");
            int accId = sc.nextInt();
            System.out.print("Enter Amount to Deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            con.setAutoCommit(false);
            PreparedStatement ps1 = con.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
            ps1.setDouble(1, amount);
            ps1.setInt(2, accId);
            int updated = ps1.executeUpdate();

            if (updated == 0) {
                System.out.println("Account not found.");
                con.rollback();
                return;
            }

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions(account_id, type, amount) VALUES (?, 'DEPOSIT', ?)");
            ps2.setInt(1, accId);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

            con.commit();
            System.out.println("Deposit successful.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void withdraw(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) { System.out.println("DB Connection failed."); return; }
        try {
            System.out.print("Enter Account ID: ");
            int accId = sc.nextInt();
            System.out.print("Enter Amount to Withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            con.setAutoCommit(false);
            PreparedStatement psCheck = con.prepareStatement("SELECT balance FROM accounts WHERE id = ?");
            psCheck.setInt(1, accId);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                System.out.println("Account not found.");
                con.rollback();
                return;
            }
            double balance = rs.getDouble("balance");
            if (balance < amount) {
                System.out.println("Insufficient funds.");
                con.rollback();
                return;
            }

            PreparedStatement ps1 = con.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
            ps1.setDouble(1, amount);
            ps1.setInt(2, accId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions(account_id, type, amount) VALUES (?, 'WITHDRAW', ?)");
            ps2.setInt(1, accId);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

            con.commit();
            System.out.println("Withdrawal successful.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void checkBalance(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) { System.out.println("DB Connection failed."); return; }
        try {
            System.out.print("Enter Account ID: ");
            int accId = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement("SELECT balance FROM accounts WHERE id = ?");
            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Balance: " + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void viewTransactions(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) { System.out.println("DB Connection failed."); return; }
        try {
            System.out.print("Enter Account ID: ");
            int accId = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE account_id = ?");
            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("type") + " | " +
                                   rs.getDouble("amount") + " | " +
                                   rs.getTimestamp("created_at"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
