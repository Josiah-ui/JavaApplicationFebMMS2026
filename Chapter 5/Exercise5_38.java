// Exercise 5.38 (Computer-Assisted Instruction: Difficulty Levels)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_38 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static int correctAnswer;
    private static int level;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int correct = 0;
        int incorrect = 0;

        level = getLevel(input);
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
                level = getLevel(input);   // the next student chooses a difficulty level
                newQuestion = true;
            }

            if (newQuestion)
                createQuestion();

            System.out.print("Your answer (-1 to quit): ");
            answer = input.nextInt();
        }
    }

    // level 1 = one-digit numbers, level 2 = up to two digits, and so on (limited to 4 so products fit in an int)
    public static int getLevel(Scanner input) {
        System.out.print("Enter the difficulty level (1-4): ");
        int chosen = input.nextInt();
        while (chosen < 1 || chosen > 4) {
            System.out.print("Invalid level. Enter the difficulty level (1-4): ");
            chosen = input.nextInt();
        }
        return chosen;
    }

    // positive number with at most 'level' digits
    public static int randomOperand() {
        int max = (int) Math.pow(10, level) - 1;
        return 1 + randomNumbers.nextInt(max);
    }

    public static void createQuestion() {
        int first = randomOperand();
        int second = randomOperand();
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
 * Enter the difficulty level (1-4): 2
 *
 * How much is 21 times 82?
 * Your answer (-1 to quit): 1722
 * Nice work!
 *
 * How much is 81 times 44?
 * Your answer (-1 to quit): 3565
 * No. Please try again.
 * Your answer (-1 to quit): 3564
 * Keep up the good work!
 *
 * How much is 4 times 22?
 * Your answer (-1 to quit): 88
 * Excellent!
 *
 * How much is 47 times 87?
 * Your answer (-1 to quit): 4089
 * Very good!
 *
 * How much is 15 times 40?
 * Your answer (-1 to quit): 600
 * Nice work!
 *
 * How much is 26 times 38?
 * Your answer (-1 to quit): 988
 * Very good!
 *
 * How much is 15 times 80?
 * Your answer (-1 to quit): 1201
 * ... (5 more lines omitted) ...
 * Your answer (-1 to quit): 2046
 * Nice work!
 *
 * Correct: 8  Incorrect: 2  Score: 80%
 * Congratulations, you are ready to go to the next level!
 * --- Next student ---
 * Enter the difficulty level (1-4): 3
 *
 * How much is 842 times 545?
 * Your answer (-1 to quit): 458890
 * Excellent!
 *
 * How much is 59 times 191?
 * Your answer (-1 to quit): -1
 */
