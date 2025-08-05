package com.project.dao;

import java.sql.*;
import java.util.Scanner;
import com.project.utils.DBConnection;

public class EmployeeDAO {
    public void addEmployee(Scanner sc) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            System.out.println("DB Connection failed.");
            return;
        }
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO employees(name, email, salary) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            System.out.println("DB Connection failed.");
            return;
        }
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email") + " | " +
                                   rs.getDouble("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
