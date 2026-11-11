// Exercise 5.30 (Guess the Number)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_30 {
    private static final SecureRandom randomNumbers = new SecureRandom();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char playAgain;

        do {
            int answer = 1 + randomNumbers.nextInt(1000);
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
            }

            System.out.println("Congratulations. You guessed the number!");
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
 * Too high. Try again.
 * ? 250
 * Too high. Try again.
 * ? 125
 * Too low. Try again.
 * ? 187
 * Too high. Try again.
 * ? 156
 * Too high. Try again.
 * ? 140
 * Too high. Try again.
 * ? 132
 * Too high. Try again.
 * ? 128
 * Too low. Try again.
 * ? 130
 * Too low. Try again.
 * ? 131
 * Congratulations. You guessed the number!
 * Would you like to play again (y or n)? y
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
 * Too low. Try again.
 * ? 983
 * Too high. Try again.
 * ? 982
 * Congratulations. You guessed the number!
 * Would you like to play again (y or n)? n
 */
