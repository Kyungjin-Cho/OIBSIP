import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizApp {

  private JFrame frame;
  private JTextField answer;
  int lifeRemaining = 10;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          QuizApp window = new QuizApp();
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
  public QuizApp() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    int randomNumber = (int) (Math.random() * 100) + 1;
    System.out.println(randomNumber);
    frame = new JFrame();
    frame.setBounds(100, 100, 1044, 621);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1026, 574);
    panel.setBackground(new Color(237, 247, 245));
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    JLabel life = new JLabel("Life : " + lifeRemaining);
    life.setFont(new Font("Segoe print", Font.BOLD, 40));
    life.setBounds(34, 23, 213, 46);
    life.setForeground(new Color(250, 102, 144));

    panel.add(life);

    JLabel lblPickANumber = new JLabel("Guess a number from 1 - 100");
    lblPickANumber.setHorizontalAlignment(SwingConstants.CENTER);
    lblPickANumber.setFont(new Font("Segoe print", Font.BOLD, 40));
    lblPickANumber.setBounds(162, 103, 715, 46);
    lblPickANumber.setForeground(new Color(174, 127, 108));
    panel.add(lblPickANumber);

    answer = new JTextField();
    answer.setHorizontalAlignment(SwingConstants.CENTER);
    answer.setFont(new Font("Segoe print", Font.BOLD, 40));
    answer.setBounds(162, 272, 721, 63);
    panel.add(answer);
    answer.setColumns(10);

    JButton guess = new JButton("Gusee Number");

    guess.setFont(new Font("Segoe print", Font.BOLD, 30));
    guess.setBounds(162, 364, 300, 63);
    guess.setBackground(new Color(183, 215, 216));
    panel.add(guess);

    JButton retry = new JButton("Retry");
    retry.setFont(new Font("Segoe print", Font.BOLD, 30));
    retry.setBounds(580, 364, 300, 63);
    retry.setBackground(new Color(254, 192, 207));
    panel.add(retry);

    JLabel res = new JLabel("Good Luck!");
    res.setForeground(new Color(32, 78, 95));
    res.setFont(new Font("Segoe print", Font.BOLD, 40));
    res.setHorizontalAlignment(SwingConstants.CENTER);
    res.setBounds(162, 460, 721, 57);
    panel.add(res);

    guess.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int ans = Integer.parseInt(answer.getText());
        res.setForeground(new Color(32, 78, 95));
        if (lifeRemaining <= 0) {
          res.setText("The answer was " + randomNumber);
          return;
        }
        res.setForeground(new Color(255, 198, 168));
        res.setFont(new Font("Segoe print", Font.BOLD, 20));
        if (ans == randomNumber) {
          res.setText("You got it right in " + (10 - lifeRemaining) + " tries! The answer is " + randomNumber);
          return;
        }
        life.setText("Life : " + --lifeRemaining);
        res.setFont(new Font("Segoe print", Font.BOLD, 40));
        res.setForeground(new Color(254, 192, 207));
        if (ans > randomNumber) {
          res.setText("Too much");
        } else {
          res.setText("Too little");
        }
      }
    });
  }
}