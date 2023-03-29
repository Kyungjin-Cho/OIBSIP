import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AtmMenu extends JFrame {

  private int balance = 10000;
  private ArrayList<String> transactions = new ArrayList<>();

  public AtmMenu() {
    setTitle("ATM Menu");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(247, 237, 226));
    panel.setLayout(new GridLayout(5, 1));

    JScrollPane scrollPane = new JScrollPane(transactionHistoryArea);
    add(scrollPane, BorderLayout.CENTER);

    JButton transactionsButton = new JButton("1. Transactions History");
    transactionsButton.setBackground(new Color(255, 211, 182));
    transactionsButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    transactionsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showTransactions();
      }
    });
    panel.add(transactionsButton);

    JButton withdrawButton = new JButton("2. Withdraw");
    withdrawButton.setBackground(new Color(182, 228, 255));
    withdrawButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    withdrawButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showWithdraw();
      }
    });

    panel.add(withdrawButton);

    JButton depositButton = new JButton("3. Deposit");
    depositButton.setBackground(new Color(213, 255, 182));
    depositButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    depositButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showDeposit();
      }
    });

    panel.add(depositButton);

    JButton transferButton = new JButton("4. Transfer");
    transferButton.setBackground(new Color(255, 182, 216));
    transferButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    transferButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showTransfer();
      }
    });

    panel.add(transferButton);

    JButton quitButton = new JButton("5. Quit");
    quitButton.setBackground(new Color(222, 182, 255));
    quitButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

    panel.add(quitButton);

    add(panel);

    setLocationRelativeTo(null);
  }

  private JTextArea transactionHistoryArea = new JTextArea();

  private void showTransactions() {
    transactionHistoryArea.setText("Transaction History:\n\n");
    double totalDeposit = 0.0;
    double totalWithdrawal = 0.0;
    double totalTransfer = 0.0;

    for (String transaction : transactions) {
      transactionHistoryArea.append(transaction + "\n");
      String[] parts = transaction.split(" \\| ");
      if (parts.length == 3) {
        String type = parts[1];
        double amount = Double.parseDouble(parts[2].substring(2));
        if (type.equals("Deposit")) {
          totalDeposit += amount;
        } else if (type.equals("Withdraw")) {
          totalWithdrawal += amount;
        } else if (type.equals("Transfer")) {
          totalTransfer += amount;
        }
      }
    }

    transactionHistoryArea.append("\n\nSummary:\n");
    transactionHistoryArea.append(String.format("Total Deposit: $%.2f\n", totalDeposit));
    transactionHistoryArea.append(String.format("Total Withdrawal: $%.2f\n", totalWithdrawal));
    transactionHistoryArea.append(String.format("Total Transfer: $%.2f\n", totalTransfer));
    transactionHistoryArea.append(String.format("Current Balance: $%.2f", balance));

    JOptionPane.showMessageDialog(null, new JScrollPane(transactionHistoryArea), "Transaction History",
        JOptionPane.PLAIN_MESSAGE);
  }

  private void showWithdraw() {
    String currentBalance = "Current Balance: $" + balance;
    String input = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Withdrawal Amount:", "Withdraw",
        JOptionPane.PLAIN_MESSAGE);
    try {
      double amount = Double.parseDouble(input);
      if (amount > balance) {
        JOptionPane.showMessageDialog(null, "Insufficient Balance", "Withdraw", JOptionPane.ERROR_MESSAGE);
      } else {
        balance -= amount;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String transaction = dateFormat.format(new Date()) + " | Withdraw | -$" + String.format("%.2f", amount);
        transactions.add(transaction);
        JOptionPane.showMessageDialog(null, "Withdrawal successful. Current balance: " + balance, "Withdraw",
            JOptionPane.PLAIN_MESSAGE);
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid Amount", "Withdraw", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void showDeposit() {
    String currentBalance = "Current Balance: $" + balance;
    String amountStr = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Deposit Amount:", "Deposit",
        JOptionPane.PLAIN_MESSAGE);
    if (amountStr != null && !amountStr.isEmpty()) {
      try {
        double amount = Double.parseDouble(amountStr);
        balance += amount;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String transaction = dateFormat.format(new Date()) + " | Deposit | $" + String.format("%.2f", amount);
        transactions.add(transaction);
        JOptionPane.showMessageDialog(this, "Deposit successful. Current balance: " + balance, "Deposit",
            JOptionPane.PLAIN_MESSAGE);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid Amount", "Deposit", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void showTransfer() {
    // Create a new panel with a grid layout
    JPanel transferPanel = new JPanel(new GridLayout(4, 1));

    // Create labels and text fields for user input
    JLabel toLabel = new JLabel("Transfer To:");
    JTextField toField = new JTextField(20);
    JLabel amountLabel = new JLabel("Amount:");
    JTextField amountField = new JTextField(20);

    // Add the labels and text fields to the panel
    transferPanel.add(toLabel);
    transferPanel.add(toField);
    transferPanel.add(amountLabel);
    transferPanel.add(amountField);

    // Display a confirmation dialog to the user
    int option = JOptionPane.showConfirmDialog(this, transferPanel, "Transfer", JOptionPane.OK_CANCEL_OPTION);

    // If the user confirms the transfer, update the balance
    if (option == JOptionPane.OK_OPTION) {
      try {
        String toUser = toField.getText();
        double amount = Double.parseDouble(amountField.getText());

        // Perform the transfer
        if (amount > balance) {
          JOptionPane.showMessageDialog(this, "Insufficient funds.");
        } else {
          balance -= amount;
          JOptionPane.showMessageDialog(this,
              String.format("Transferred %.2f to %s.\nCurrent balance: %.2f", amount, toUser, balance));
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input.");
      }
    }
  }

}
