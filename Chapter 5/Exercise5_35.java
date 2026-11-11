// Exercise 5.35 (Computer-Assisted Instruction)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_35 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static int correctAnswer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        createQuestion();   // first question
        System.out.print("Your answer (-1 to quit): ");
        int answer = input.nextInt();

        while (answer != -1) {
            if (answer == correctAnswer) {
                System.out.println("Very good!");
                createQuestion();   // new question after a correct answer
            } else {
                System.out.println("No. Please try again.");
            }
            System.out.print("Your answer (-1 to quit): ");
            answer = input.nextInt();
        }
    }

    // creates and displays a new multiplication question
    public static void createQuestion() {
        int first = 1 + randomNumbers.nextInt(9);
        int second = 1 + randomNumbers.nextInt(9);
        correctAnswer = first * second;
        System.out.printf("%nHow much is %d times %d?%n", first, second);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * How much is 1 times 9?
 * Your answer (-1 to quit): 9
 * Very good!
 *
 * How much is 3 times 8?
 * Your answer (-1 to quit): 25
 * No. Please try again.
 * Your answer (-1 to quit): 24
 * Very good!
 *
 * How much is 5 times 6?
 * Your answer (-1 to quit): 30
 * Very good!
 *
 * How much is 2 times 7?
 * Your answer (-1 to quit): -1
 */
