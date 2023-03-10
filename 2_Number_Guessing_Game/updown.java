import java.util.Scanner;

public class updown {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Please enter a number");
    int input = Integer.parseInt(sc.nextLine());

    int answer = (int) (Math.random() * 100) + 1;

    while (answer != input) {
      if (answer > input) {
        System.out.println("UP");
      }
      if (answer < input) {
        System.out.println("DOWN");
      }
      input = Integer.parseInt(sc.nextLine());
    }

    if (input == answer) {
      System.out.println("Congrats! The answer was " + answer);
    }
  }
}