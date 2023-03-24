import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class QuizApp {

  JFrame frame;
  private JTextField answer;
  private int lifeRemaining = 10;
  private int randomNumber;
  private int roundNum = 1;

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
    randomNumber = (int) (Math.random() * 100) + 1;
    System.out.println(randomNumber);
    frame = new JFrame();
    frame.setBounds(100, 100, 1044, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1026, 550);
    panel.setBackground(new Color(247, 237, 226));
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    JLabel life = new JLabel("Life : " + lifeRemaining);
    life.setFont(new Font("Segoe print", Font.BOLD, 40));
    life.setBounds(34, 23, 213, 46);
    life.setForeground(new Color(246, 189, 96));

    panel.add(life);

    JLabel explanation = new JLabel("Guess a number from 1 - 100");
    explanation.setHorizontalAlignment(SwingConstants.CENTER);
    explanation.setFont(new Font("Segoe print", Font.BOLD, 40));
    explanation.setBounds(162, 103, 715, 46);
    explanation.setForeground(new Color(174, 127, 108));
    panel.add(explanation);

    JLabel result = new JLabel("Round " + roundNum + "!");
    result.setForeground(new Color(132, 165, 157));
    result.setFont(new Font("Segoe print", Font.BOLD, 50));
    result.setHorizontalAlignment(SwingConstants.CENTER);
    result.setBounds(162, 180, 721, 57);
    panel.add(result);

    answer = new JTextField();
    answer.setHorizontalAlignment(SwingConstants.CENTER);
    answer.setFont(new Font("Segoe print", Font.BOLD, 40));
    answer.setBounds(162, 272, 721, 63);
    answer.setBackground(new Color(245, 202, 195));
    panel.add(answer);
    answer.setColumns(10);

    JButton guess = new JButton("Gusee Number");

    guess.setFont(new Font("Segoe print", Font.BOLD, 30));
    guess.setBounds(162, 400, 300, 63);
    guess.setBackground(new Color(183, 215, 216));
    panel.add(guess);

    JButton retry = new JButton("Retry");
    retry.setFont(new Font("Segoe print", Font.BOLD, 30));
    retry.setBounds(580, 400, 300, 63);
    retry.setBackground(new Color(254, 192, 207));
    panel.add(retry);

    retry.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        lifeRemaining = 10;
        randomNumber = (int) (Math.random() * 100) + 1;
        roundNum++;
        life.setText("Life : " + lifeRemaining);
        result.setFont(new Font("Segoe print", Font.BOLD, 50));
        result.setText("Round " + roundNum + "!");
        answer.setText("");
      }
    });

    guess.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int ans = Integer.parseInt(answer.getText());
        result.setForeground(new Color(132, 165, 157));
        if (lifeRemaining <= 1) {
          result.setText("The answer was " + randomNumber);
          life.setText("No Life!");
          return;
        }
        result.setForeground(new Color(132, 165, 157));
        result.setFont(new Font("Segoe print", Font.BOLD, 20));
        if (ans == randomNumber) {
          result.setText("You got it right in " + (10 - lifeRemaining) + " tries! The answer is " + randomNumber);
          return;
        }
        life.setText("Life : " + --lifeRemaining);
        result.setFont(new Font("Segoe print", Font.BOLD, 40));
        result.setForeground(new Color(242, 132, 130));
        if (ans > randomNumber) {
          result.setText("Too much");
        } else {
          result.setText("Too little");
        }
      }
    });
  }
}