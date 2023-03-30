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
    // declare randome number
    randomNumber = (int) (Math.random() * 100) + 1;

    // create a frame
    frame = new JFrame();
    // set frame size
    frame.setBounds(100, 100, 1044, 550);
    // set close function
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // position components absolutely
    frame.getContentPane().setLayout(null);

    // create a panel
    JPanel panel = new JPanel();
    // set panel size
    panel.setBounds(0, 0, 1026, 550);
    // set panel background
    panel.setBackground(new Color(247, 237, 226));
    // position components absolutely
    panel.setLayout(null);
    // add panel to frame
    frame.getContentPane().add(panel);

    // create a label "life" for showing remaing life
    JLabel life = new JLabel("Life : " + lifeRemaining);
    // set label font style
    life.setFont(new Font("Segoe print", Font.BOLD, 40));
    // set size and location of label
    life.setBounds(34, 23, 213, 46);
    // set label font color
    life.setForeground(new Color(246, 189, 96));
    // add label to panel
    panel.add(life);

    // create a label "explanation" showing game guideline
    JLabel explanation = new JLabel("Guess a number from 1 - 100");
    // set its location horizonntally center
    explanation.setHorizontalAlignment(SwingConstants.CENTER);
    // set label font style
    explanation.setFont(new Font("Segoe print", Font.BOLD, 40));
    // set size and location of label
    explanation.setBounds(162, 103, 715, 46);
    // set label font color
    explanation.setForeground(new Color(174, 127, 108));
    // add label to panel
    panel.add(explanation);

    // create a lbel "result" showing round number and result
    JLabel result = new JLabel("Round " + roundNum + "!");
    // set label font color
    result.setForeground(new Color(132, 165, 157));
    // set label font style
    result.setFont(new Font("Segoe print", Font.BOLD, 50));
    // set its location horizontally center
    result.setHorizontalAlignment(SwingConstants.CENTER);
    // set size and location of label
    result.setBounds(162, 180, 721, 57);
    // add label to panel
    panel.add(result);

    // create a text field "answer" receiving input
    answer = new JTextField();
    // set its location horizontally center
    answer.setHorizontalAlignment(SwingConstants.CENTER);
    // set text field font style
    answer.setFont(new Font("Segoe print", Font.BOLD, 40));
    // set size and location of text field
    answer.setBounds(162, 272, 721, 63);
    // set text field background color
    answer.setBackground(new Color(245, 202, 195));
    // set the number of columns of text field
    answer.setColumns(10);
    // add text field to panel
    panel.add(answer);

    // create a button "guess" allowing users to check answer
    JButton guess = new JButton("Gusee Number");
    // set button font style
    guess.setFont(new Font("Segoe print", Font.BOLD, 30));
    // set size and location of button
    guess.setBounds(162, 400, 300, 63);
    // set button background color
    guess.setBackground(new Color(183, 215, 216));
    // add button to panel
    panel.add(guess);

    // add action(function) to guess button
    guess.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        // covert input(String type) into number(Integer type)
        int ans = Integer.parseInt(answer.getText());
        // set label font color
        result.setForeground(new Color(132, 165, 157));
        // if only a life is left, show answer and life text once users click button
        if (lifeRemaining <= 1) {
          // show answer to users
          result.setText("The answer was " + randomNumber);
          // change text of life
          life.setText("No Life!");
          return;
        }
        // set label font color
        result.setForeground(new Color(132, 165, 157));
        // set label font style
        result.setFont(new Font("Segoe print", Font.BOLD, 20));
        // if users get answer in 10 times, show answer and the number of tries
        if (ans == randomNumber) {
          result.setText("You got it right in " + (10 - lifeRemaining) + " tries! The answer is " + randomNumber);
          return;
        }
        // decrease the number of life once users click button
        life.setText("Life : " + --lifeRemaining);
        // set label font style
        result.setFont(new Font("Segoe print", Font.BOLD, 40));
        // set label font color
        result.setForeground(new Color(242, 132, 130));

        // show hint to users when they guess answers
        if (ans > randomNumber) {
          // show "Too much" if input is bigger than answer
          result.setText("Too much");
        } else {
          // show "Too little" if input is smaller than answer
          result.setText("Too little");
        }
      }
    });

    // create a button "retry" allowing users to play another game(round)
    JButton retry = new JButton("Retry");
    // set button font style
    retry.setFont(new Font("Segoe print", Font.BOLD, 30));
    // set size and location of button
    retry.setBounds(580, 400, 300, 63);
    // set button background color
    retry.setBackground(new Color(254, 192, 207));
    // add button to panel
    panel.add(retry);

    // // add action(function) to retry button
    retry.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        // initialize life(set it as 10)
        lifeRemaining = 10;
        // create anoter answer(random number)
        randomNumber = (int) (Math.random() * 100) + 1;
        // increase the number of round
        roundNum++;
        // show the number of life
        life.setText("Life : " + lifeRemaining);
        // set label font color
        result.setFont(new Font("Segoe print", Font.BOLD, 50));
        // show the number of round
        result.setText("Round " + roundNum + "!");
        // clear text field
        answer.setText("");
      }
    });

  }
}