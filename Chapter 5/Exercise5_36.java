// Exercise 5.36 (Computer-Assisted Instruction: Reducing Student Fatigue)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_36 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static int correctAnswer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        createQuestion();
        System.out.print("Your answer (-1 to quit): ");
        int answer = input.nextInt();

        while (answer != -1) {
            if (answer == correctAnswer) {
                correctResponse();
                createQuestion();
            } else {
                incorrectResponse();
            }
            System.out.print("Your answer (-1 to quit): ");
            answer = input.nextInt();
        }
    }

    public static void createQuestion() {
        int first = 1 + randomNumbers.nextInt(9);
        int second = 1 + randomNumbers.nextInt(9);
        correctAnswer = first * second;
        System.out.printf("%nHow much is %d times %d?%n", first, second);
    }

    // random comment for a correct answer
    public static void correctResponse() {
        switch (1 + randomNumbers.nextInt(4)) {
            case 1:
                System.out.println("Very good!");
                break;
            case 2:
                System.out.println("Excellent!");
                break;
            case 3:
                System.out.println("Nice work!");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
        }
    }

    // random comment for an incorrect answer
    public static void incorrectResponse() {
        switch (1 + randomNumbers.nextInt(4)) {
            case 1:
                System.out.println("No. Please try again.");
                break;
            case 2:
                System.out.println("Wrong. Try once more.");
                break;
            case 3:
                System.out.println("Don't give up!");
                break;
            case 4:
                System.out.println("No. Keep trying.");
                break;
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * How much is 3 times 2?
 * Your answer (-1 to quit): 6
 * Very good!
 *
 * How much is 8 times 2?
 * Your answer (-1 to quit): 17
 * Don't give up!
 * Your answer (-1 to quit): 17
 * No. Keep trying.
 * Your answer (-1 to quit): 16
 * Excellent!
 *
 * How much is 2 times 9?
 * Your answer (-1 to quit): 18
 * Keep up the good work!
 *
 * How much is 2 times 1?
 * Your answer (-1 to quit): 3
 * Wrong. Try once more.
 * Your answer (-1 to quit): 2
 * Keep up the good work!
 *
 * How much is 6 times 6?
 * Your answer (-1 to quit): -1
 */
