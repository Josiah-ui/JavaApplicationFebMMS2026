// Exercise 5.31 (Guess the Number Modification)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_31 {
    private static final SecureRandom randomNumbers = new SecureRandom();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char playAgain;

        do {
            int answer = 1 + randomNumbers.nextInt(1000);
            int guesses = 1;
            System.out.println("Guess a number between 1 and 1000");
            System.out.print("? ");
            int guess = input.nextInt();

            while (guess != answer) {
                if (guess > answer)
                    System.out.println("Too high. Try again.");
                else
                    System.out.println("Too low. Try again.");
                System.out.print("? ");
                guess = input.nextInt();
                guesses++;
            }

            System.out.println("Congratulations. You guessed the number!");
            if (guesses == 10)
                System.out.println("Aha! You know the secret!");
            else if (guesses < 10)
                System.out.println("Either you know the secret or you got lucky!");
            else
                System.out.println("You should be able to do better!");
            System.out.printf("Number of guesses: %d%n", guesses);

            System.out.print("Would you like to play again (y or n)? ");
            playAgain = input.next().charAt(0);
        } while (playAgain == 'y' || playAgain == 'Y');
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Guess a number between 1 and 1000
 * ? 500
 * Too low. Try again.
 * ? 750
 * Too low. Try again.
 * ? 875
 * Too low. Try again.
 * ? 938
 * Too low. Try again.
 * ? 969
 * Too low. Try again.
 * ? 985
 * Too high. Try again.
 * ? 977
 * Too low. Try again.
 * ? 981
 * Too high. Try again.
 * ? 979
 * Congratulations. You guessed the number!
 * Either you know the secret or you got lucky!
 * Number of guesses: 9
 * Would you like to play again (y or n)? y
 * Guess a number between 1 and 1000
 * ? 500
 * Too high. Try again.
 * ? 250
 * Too high. Try again.
 * ? 125
 * Too high. Try again.
 * ? 62
 * Too low. Try again.
 * ? 93
 * Too high. Try again.
 * ? 77
 * Too low. Try again.
 * ? 85
 * Too high. Try again.
 * ? 81
 * Too low. Try again.
 * ? 83
 * Congratulations. You guessed the number!
 * Either you know the secret or you got lucky!
 * Number of guesses: 9
 * Would you like to play again (y or n)? n
 */
