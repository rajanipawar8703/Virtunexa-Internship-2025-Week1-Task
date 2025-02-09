package ui;

import expense.ExpenseManager;

import java.util.Scanner;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nExpense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View Expenses");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter category: ");
                    String category = scanner.next();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.next();
                    manager.addExpense(category, amount, date);
                }
                case 2 -> {
                    System.out.print("Enter expense ID to edit: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new category: ");
                    String category = scanner.next();
                    System.out.print("Enter new amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String date = scanner.next();
                    manager.editExpense(id, category, amount, date);
                }
                case 3 -> {
                    System.out.print("Enter expense ID to delete: ");
                    int id = scanner.nextInt();
                    manager.deleteExpense(id);
                }
                case 4 -> manager.viewExpenses();
                case 5 -> manager.generateReport();  // Calling the method to generate report
                case 6 -> System.out.println("Exiting Expense Tracker. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
