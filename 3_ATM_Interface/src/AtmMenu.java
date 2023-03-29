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
    setSize(600, 380);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(247, 237, 226));
    panel.setLayout(new GridLayout(4, 1));

    JTextArea transactionHistoryArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(transactionHistoryArea);
    panel.add(scrollPane);

    JButton transactionsButton = new JButton("1. Transactions History");
    transactionsButton.setBackground(new Color(255, 211, 182));
    transactionsButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    transactionsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showTransactions(transactionHistoryArea);
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

    JLabel oasisAtm = new JLabel("OASIS INFOBYTE ATM");
    oasisAtm.setFont(new Font("Segoe print", Font.BOLD, 25));
    oasisAtm.setForeground(new Color(174, 127, 108));
    panel.add(oasisAtm);

    add(panel);

    setLocationRelativeTo(null);
  }

  private void showTransactions(JTextArea transactionHistoryArea) {
    JOptionPane.showMessageDialog(null, "Current balance: " + balance, "Transaction History",
        JOptionPane.PLAIN_MESSAGE);

    transactionHistoryArea.setText("Transaction History:\n\n");

    for (String transaction : transactions) {
      transactionHistoryArea.append(transaction + "\n");

    }
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
    String currentBalance = "Current Balance: $" + balance;
    String input = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Transfer Amount:", "Transfer",
        JOptionPane.PLAIN_MESSAGE);
    try {
      double amount = Double.parseDouble(input);
      if (amount > balance) {
        JOptionPane.showMessageDialog(null, "Insufficient Balance", "Transfer", JOptionPane.ERROR_MESSAGE);
      } else {
        String recipient = JOptionPane.showInputDialog(null, "Enter Recipient's User ID:", "Transfer",
            JOptionPane.PLAIN_MESSAGE);
        if (recipient == null || recipient.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Recipient's Account Number is Required", "Transfer",
              JOptionPane.ERROR_MESSAGE);
        } else {
          balance -= amount;
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          String transaction = dateFormat.format(new Date()) + " | Transfer to " + recipient + " | -$"
              + String.format("%.2f", amount);
          transactions.add(transaction);
          JOptionPane.showMessageDialog(null,
              "Transfer successful. Current balance: " + balance + "\nRecipient: " + recipient, "Transfer",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid Amount", "Transfer", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    AtmMenu atmMenu = new AtmMenu();
    atmMenu.setVisible(true);
  }
}
