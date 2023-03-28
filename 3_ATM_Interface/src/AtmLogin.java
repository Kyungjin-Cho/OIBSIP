import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AtmLogin {

  private JFrame frame;
  private JTextField userIDField;
  private JTextField userPinField;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AtmLogin window = new AtmLogin();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public AtmLogin() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1044, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1026, 550);
    panel.setBackground(new Color(247, 237, 226));
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    JLabel loginNotice = new JLabel("Please enter user ID and user pin.");
    loginNotice.setFont(new Font("Segoe print", Font.BOLD, 20));
    loginNotice.setBounds(34, 23, 400, 46);
    loginNotice.setForeground(new Color(246, 189, 96));
    panel.add(loginNotice);

    JLabel userID = new JLabel("ID   : ");
    userID.setFont(new Font("Segoe print", Font.BOLD, 40));
    userID.setBounds(162, 150, 213, 46);
    userID.setForeground(new Color(174, 127, 108));
    panel.add(userID);

    userIDField = new JTextField();
    userIDField.setBounds(350, 150, 300, 46);
    userIDField.setFont(new Font("Segoe print", Font.BOLD, 20));
    panel.add(userIDField);

    JLabel userPin = new JLabel("PIN : ");
    userPin.setFont(new Font("Segoe print", Font.BOLD, 40));
    userPin.setBounds(162, 300, 213, 46);
    userPin.setForeground(new Color(174, 127, 108));
    panel.add(userPin);

    userPinField = new JTextField();
    userPinField.setBounds(350, 300, 300, 46);
    userPinField.setFont(new Font("Segoe print", Font.BOLD, 20));
    panel.add(userPinField);

    JButton enterButton = new JButton("Enter");
    enterButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    enterButton.setBounds(800, 120, 150, 100);
    enterButton.setBackground(new Color(183, 215, 216));
    panel.add(enterButton);
    enterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String userID = userIDField.getText();
        String userPin = userPinField.getText();
        String correctID = "oasis";
        String correctPin = "2023";
        if (userID.equals(correctID) && userPin.equals(correctPin)) {
          frame.setVisible(false);
          new AtmMenu().setVisible(true);
        } else {
          JOptionPane.showMessageDialog(null, "Invalid User ID or PIN.\nPlease enter the ID and PIN again");
        }
      }
    });

    JButton clearButton = new JButton("Clear");
    clearButton.setFont(new Font("Segoe print", Font.BOLD, 20));
    clearButton.setBounds(800, 270, 150, 100);
    clearButton.setBackground(new Color(254, 192, 207));
    panel.add(clearButton);
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        userIDField.setText("");
        userPinField.setText("");
      }
    });

    // Align buttons vertically
    enterButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    clearButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

  }
}
