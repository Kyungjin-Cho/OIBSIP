import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AtmMenu extends JFrame {
  public AtmMenu() {
    setTitle("ATM Menu");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(247, 237, 226));
    panel.setLayout(new GridLayout(5, 1));

    JButton transactionsButton = new JButton("1. Transactions History");
    transactionsButton.setBackground(new Color(255, 211, 182));
    transactionsButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    JButton withdrawButton = new JButton("2. Withdraw");
    withdrawButton.setBackground(new Color(182, 228, 255));
    withdrawButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    JButton depositButton = new JButton("3. Deposit");
    depositButton.setBackground(new Color(213, 255, 182));
    depositButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    JButton transferButton = new JButton("4. Transfer");
    transferButton.setBackground(new Color(255, 182, 216));
    transferButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    JButton quitButton = new JButton("5. Quit");
    quitButton.setBackground(new Color(222, 182, 255));
    quitButton.setFont(new Font("Segoe print", Font.BOLD, 20));

    panel.add(transactionsButton);
    panel.add(withdrawButton);
    panel.add(depositButton);
    panel.add(transferButton);
    panel.add(quitButton);

    add(panel);

    setLocationRelativeTo(null);
  }
}
