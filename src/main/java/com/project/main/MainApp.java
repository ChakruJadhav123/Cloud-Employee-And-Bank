package com.project.main;

import java.util.Scanner;
import com.project.dao.EmployeeDAO;
import com.project.dao.BankingDAO;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        BankingDAO bankingDAO = new BankingDAO();

        while (true) {
            System.out.println("\n==== Cloud EMS + Banking ====");
            System.out.println("1. Employee Module");
            System.out.println("2. Banking Module");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nEmployee Module:");
                    System.out.println("1. Add Employee");
                    System.out.println("2. View Employees");
                    System.out.print("Choose: ");
                    int empChoice = sc.nextInt();
                    sc.nextLine();
                    if (empChoice == 1) employeeDAO.addEmployee(sc);
                    else employeeDAO.viewEmployees();
                }
                case 2 -> bankingDAO.handleBanking(sc);
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
