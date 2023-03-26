import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AtmApp {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AtmApp window = new AtmApp();
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
  public AtmApp() {
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

    JLabel userPin = new JLabel("PIN : ");
    userPin.setFont(new Font("Segoe print", Font.BOLD, 40));
    userPin.setBounds(162, 300, 213, 46);
    userPin.setForeground(new Color(174, 127, 108));
    panel.add(userPin);
  }
}