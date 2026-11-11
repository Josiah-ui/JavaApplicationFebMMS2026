// Exercise 5.37 (Computer-Assisted Instruction: Monitoring Student Performance)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_37 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static int correctAnswer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int correct = 0;
        int incorrect = 0;

        createQuestion();
        System.out.print("Your answer (-1 to quit): ");
        int answer = input.nextInt();

        while (answer != -1) {
            boolean newQuestion = false;

            if (answer == correctAnswer) {
                correct++;
                correctResponse();
                newQuestion = true;
            } else {
                incorrect++;
                incorrectResponse();
            }

            // after 10 answers, report the percentage and reset for the next student
            if (correct + incorrect == 10) {
                double percentage = 100.0 * correct / 10;
                System.out.printf("%nCorrect: %d  Incorrect: %d  Score: %.0f%%%n", correct, incorrect, percentage);
                if (percentage < 75)
                    System.out.println("Please ask your teacher for extra help.");
                else
                    System.out.println("Congratulations, you are ready to go to the next level!");
                System.out.println("--- Next student ---");
                correct = 0;
                incorrect = 0;
                newQuestion = true;
            }

            if (newQuestion)
                createQuestion();

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

    public static void correctResponse() {
        switch (1 + randomNumbers.nextInt(4)) {
            case 1: System.out.println("Very good!"); break;
            case 2: System.out.println("Excellent!"); break;
            case 3: System.out.println("Nice work!"); break;
            case 4: System.out.println("Keep up the good work!"); break;
        }
    }

    public static void incorrectResponse() {
        switch (1 + randomNumbers.nextInt(4)) {
            case 1: System.out.println("No. Please try again."); break;
            case 2: System.out.println("Wrong. Try once more."); break;
            case 3: System.out.println("Don't give up!"); break;
            case 4: System.out.println("No. Keep trying."); break;
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * How much is 6 times 3?
 * Your answer (-1 to quit): 18
 * Excellent!
 *
 * How much is 8 times 3?
 * Your answer (-1 to quit): 25
 * No. Please try again.
 * Your answer (-1 to quit): 24
 * Nice work!
 *
 * How much is 5 times 9?
 * Your answer (-1 to quit): 45
 * Keep up the good work!
 *
 * How much is 4 times 8?
 * Your answer (-1 to quit): 33
 * No. Please try again.
 * Your answer (-1 to quit): 32
 * Very good!
 *
 * How much is 9 times 9?
 * Your answer (-1 to quit): 81
 * Keep up the good work!
 *
 * How much is 4 times 8?
 * Your answer (-1 to quit): 32
 * Very good!
 *
 * How much is 6 times 3?
 * Your answer (-1 to quit): 19
 * No. Please try again.
 * Your answer (-1 to quit): 18
 * Nice work!
 *
 * Correct: 7  Incorrect: 3  Score: 70%
 * Please ask your teacher for extra help.
 * --- Next student ---
 *
 * How much is 6 times 8?
 * Your answer (-1 to quit): 48
 * Nice work!
 *
 * How much is 7 times 3?
 * Your answer (-1 to quit): -1
 */
