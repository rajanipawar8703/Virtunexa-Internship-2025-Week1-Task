package expense;

import db.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseManager {

    public void addExpense(String category, double amount, String date) {
        String query = "INSERT INTO expense (category, amount, date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            stmt.setDouble(2, amount);
            stmt.setString(3, date);
            stmt.executeUpdate();
            System.out.println("Expense added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editExpense(int id, String category, double amount, String date) {
        String query = "UPDATE expense SET category = ?, amount = ?, date = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            stmt.setDouble(2, amount);
            stmt.setString(3, date);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Expense updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExpense(int id) {
        String query = "DELETE FROM expense WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Expense deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewExpenses() {
        String query = "SELECT * FROM expense";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Category: " + rs.getString("category") +
                                   ", Amount: " + rs.getDouble("amount") + ", Date: " + rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
        String query = "SELECT id, category, amount, date FROM expense";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Detailed Expense Report:");
            System.out.println("------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                System.out.println("ID: " + id + " | Category: " + category + " | Amount: " + amount + " | Date: " + date);
            }
            
            // Summarizing expenses by category
            System.out.println("\nCategory-wise Summary:");
            String summaryQuery = "SELECT category, SUM(amount) AS total_amount FROM expense GROUP BY category";
            try (PreparedStatement summaryStmt = conn.prepareStatement(summaryQuery);
                 ResultSet summaryRs = summaryStmt.executeQuery()) {
                while (summaryRs.next()) {
                    String category = summaryRs.getString("category");
                    double totalAmount = summaryRs.getDouble("total_amount");
                    System.out.println("Category: " + category + " | Total Amount: " + totalAmount);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
