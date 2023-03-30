import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AtmMenu extends JFrame {
  // declare balance amount
  private int balance = 10000;
  // create an Arraylist for transaction history
  private ArrayList<String> transactions = new ArrayList<>();

  public AtmMenu() {
    // set title of frame
    setTitle("ATM Menu");
    // set size of frame
    setSize(600, 380);
    // set close function
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create a panel
    JPanel panel = new JPanel();
    // set panel background
    panel.setBackground(new Color(247, 237, 226));
    // set panel layout(grid layout)
    panel.setLayout(new GridLayout(4, 1));

    // create a text area for transaction history
    JTextArea transactionHistoryArea = new JTextArea();
    // add scroll function to text area
    JScrollPane scrollPane = new JScrollPane(transactionHistoryArea);
    // add scroll function to panel
    panel.add(scrollPane);

    // create a button for checking transaction history
    JButton transactionsButton = new JButton("1. Transactions History");
    // set button background color
    transactionsButton.setBackground(new Color(255, 211, 182));
    // set button font style
    transactionsButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    // add action(function) to transactionButton button
    transactionsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showTransactions(transactionHistoryArea);
      }
    });
    // add button to panel
    panel.add(transactionsButton);

    // create a button for withdrawing money
    JButton withdrawButton = new JButton("2. Withdraw");
    // set button background color
    withdrawButton.setBackground(new Color(182, 228, 255));
    // set button font style
    withdrawButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    // add action(function) to withdrawButton button
    withdrawButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showWithdraw();
      }
    });
    // add button to panel
    panel.add(withdrawButton);

    // create a button for depositing money
    JButton depositButton = new JButton("3. Deposit");
    // set button background color
    depositButton.setBackground(new Color(213, 255, 182));
    // set button font style
    depositButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    // add action(function) to depositButton button
    depositButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showDeposit();
      }
    });
    // add button to panel
    panel.add(depositButton);

    // create a button for transferring money
    JButton transferButton = new JButton("4. Transfer");
    // set button background color
    transferButton.setBackground(new Color(255, 182, 216));
    // set button font style
    transferButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    // add action(function) to transferButton button
    transferButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showTransfer();
      }
    });
    // add button to panel
    panel.add(transferButton);

    // create a button for qutting transaction
    JButton quitButton = new JButton("5. Quit");
    // set button background color
    quitButton.setBackground(new Color(222, 182, 255));
    // set button font style
    quitButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    // add action(function) to quitButton button
    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // quit execution once users click quitButton
        dispose();
      }
    });
    // add button to panel
    panel.add(quitButton);

    // create a label for showing name of ATM
    JLabel oasisAtm = new JLabel("OASIS INFOBYTE ATM");
    // set label font style
    oasisAtm.setFont(new Font("Segoe print", Font.BOLD, 25));
    // set label font color
    oasisAtm.setForeground(new Color(174, 127, 108));
    // add label to panel
    panel.add(oasisAtm);

    // add panel to frame
    add(panel);

    // set the location of frame(center)
    setLocationRelativeTo(null);
  }

  // set transactionButton function
  private void showTransactions(JTextArea transactionHistoryArea) {
    // show current balance
    JOptionPane.showMessageDialog(null, "Current balance: " + balance, "Transaction History",
        JOptionPane.PLAIN_MESSAGE);
    // add title of transactionHistory
    transactionHistoryArea.setText("Transaction History:\n\n");
    // add each transaction history(ArrayList) to text area
    for (String transaction : transactions) {
      transactionHistoryArea.append(transaction + "\n");

    }
  }

  // set withdrawButton function
  private void showWithdraw() {
    // allow users to check current balance and input amount
    String currentBalance = "Current Balance: $" + balance;
    String input = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Withdrawal Amount:", "Withdraw",
        JOptionPane.PLAIN_MESSAGE);
    // check validity after getting input
    try {
      // convert input(String type) to amount(Double type)
      double amount = Double.parseDouble(input);
      // if input is bigger than balance, show error message
      if (amount > balance) {
        JOptionPane.showMessageDialog(null, "Insufficient Balance", "Withdraw", JOptionPane.ERROR_MESSAGE);
        // if input is valid, proceed withdrawal
      } else {
        // update balance amount
        balance -= amount;
        // set date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // create an element of transaction(ArrayList)
        String transaction = dateFormat.format(new Date()) + " | Withdraw | -$" + String.format("%.2f", amount);
        transactions.add(transaction);
        // show current balance amount after withdrawal
        JOptionPane.showMessageDialog(null, "Withdrawal successful. Current balance: " + balance, "Withdraw",
            JOptionPane.PLAIN_MESSAGE);
      }
      // if data type of input is invalid, show error message
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid Amount", "Withdraw", JOptionPane.ERROR_MESSAGE);
    }
  }

  // set depositButton function
  private void showDeposit() {
    // allow users to check current balance and input amount
    String currentBalance = "Current Balance: $" + balance;
    String amountStr = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Deposit Amount:", "Deposit",
        JOptionPane.PLAIN_MESSAGE);
    // if input amount is valid, proceed deposit
    if (amountStr != null && !amountStr.isEmpty()) {
      try {
        // convert input(String type) to amount(Double type)
        double amount = Double.parseDouble(amountStr);
        // update balance amount
        balance += amount;
        // set date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // create an element of transaction(ArrayList)
        String transaction = dateFormat.format(new Date()) + " | Deposit | $" + String.format("%.2f", amount);
        transactions.add(transaction);
        // show current balance amount after deposit
        JOptionPane.showMessageDialog(this, "Deposit successful. Current balance: " + balance, "Deposit",
            JOptionPane.PLAIN_MESSAGE);
        // if data type of input is invalid, show error message
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid Amount", "Deposit", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      // if users input nothing, show error message
      JOptionPane.showMessageDialog(null, "Please enter the deposit amount", "Deposit", JOptionPane.ERROR_MESSAGE);
    }
  }

  // set transferButton function
  private void showTransfer() {
    // allow users to check current balance and input amount
    String currentBalance = "Current Balance: $" + balance;
    String input = JOptionPane.showInputDialog(null, currentBalance + "\nEnter Transfer Amount:", "Transfer",
        JOptionPane.PLAIN_MESSAGE);
    try {
      // convert input(String type) to amount(Double type)
      double amount = Double.parseDouble(input);
      // if input is bigger than balance, show error message
      if (amount > balance) {
        JOptionPane.showMessageDialog(null, "Insufficient Balance", "Transfer", JOptionPane.ERROR_MESSAGE);
      } else {
        // allow users to input user ID of recipient
        String recipient = JOptionPane.showInputDialog(null, "Enter Recipient's User ID:", "Transfer",
            JOptionPane.PLAIN_MESSAGE);
        // if users input nothing, show error message
        if (recipient == null || recipient.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Recipient's Account Number is Required", "Transfer",
              JOptionPane.ERROR_MESSAGE);
          // if input is valid, proceed transfer
        } else {
          // update balance amount
          balance -= amount;
          // set date format
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          // create an element of transaction(ArrayList)
          String transaction = dateFormat.format(new Date()) + " | Transfer to " + recipient + " | -$"
              + String.format("%.2f", amount);
          transactions.add(transaction);
          // show current balance amount after transfer
          JOptionPane.showMessageDialog(null,
              "Transfer successful. Current balance: " + balance + "\nRecipient: " + recipient, "Transfer",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
      // if data type of input is invalid, show error message
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid Amount", "Transfer", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    // create an AtmMenu
    AtmMenu atmMenu = new AtmMenu();
    // set atmMenu visible
    atmMenu.setVisible(true);
  }
}
