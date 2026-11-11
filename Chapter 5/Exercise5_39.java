// Exercise 5.39 (Computer-Assisted Instruction: Varying the Types of Problems)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_39 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static int correctAnswer;
    private static int level;
    private static int problemType;   // 1 = add, 2 = subtract, 3 = multiply, 4 = divide, 5 = mixture

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int correct = 0;
        int incorrect = 0;

        level = getLevel(input);
        problemType = getProblemType(input);
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
                level = getLevel(input);
                problemType = getProblemType(input);
                newQuestion = true;
            }

            if (newQuestion)
                createQuestion();

            System.out.print("Your answer (-1 to quit): ");
            answer = input.nextInt();
        }
    }

    public static int getLevel(Scanner input) {
        System.out.print("Enter the difficulty level (1-4): ");
        int chosen = input.nextInt();
        while (chosen < 1 || chosen > 4) {
            System.out.print("Invalid level. Enter the difficulty level (1-4): ");
            chosen = input.nextInt();
        }
        return chosen;
    }

    public static int getProblemType(Scanner input) {
        System.out.printf("Problem type: 1) addition  2) subtraction  3) multiplication  4) division  5) random mixture%n");
        System.out.print("Enter your choice: ");
        int chosen = input.nextInt();
        while (chosen < 1 || chosen > 5) {
            System.out.print("Invalid choice. Enter 1-5: ");
            chosen = input.nextInt();
        }
        return chosen;
    }

    public static int randomOperand() {
        int max = (int) Math.pow(10, level) - 1;
        return 1 + randomNumbers.nextInt(max);
    }

    public static void createQuestion() {
        int kind = (problemType == 5) ? 1 + randomNumbers.nextInt(4) : problemType;
        int first = randomOperand();
        int second = randomOperand();

        switch (kind) {
            case 1:
                correctAnswer = first + second;
                System.out.printf("%nHow much is %d plus %d?%n", first, second);
                break;
            case 2:
                correctAnswer = first - second;
                System.out.printf("%nHow much is %d minus %d?%n", first, second);
                break;
            case 3:
                correctAnswer = first * second;
                System.out.printf("%nHow much is %d times %d?%n", first, second);
                break;
            case 4:
                // build the dividend from the answer so the division always comes out even
                correctAnswer = first;
                System.out.printf("%nHow much is %d divided by %d?%n", first * second, second);
                break;
        }
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
 * Problem type: 1) addition  2) subtraction  3) multiplication  4) division  5) random mixture
 * Enter your choice: 5
 *
 * How much is 20 minus 3?
 * Your answer (-1 to quit): 17
 * Nice work!
 *
 * How much is 3818 divided by 83?
 * Your answer (-1 to quit): 46
 * Nice work!
 *
 * How much is 91 plus 9?
 * Your answer (-1 to quit): 101
 * No. Keep trying.
 * Your answer (-1 to quit): 100
 * Excellent!
 *
 * How much is 54 times 94?
 * Your answer (-1 to quit): 5076
 * Nice work!
 *
 * How much is 31 plus 55?
 * Your answer (-1 to quit): 86
 * Keep up the good work!
 *
 * How much is 62 minus 75?
 * Your answer (-1 to quit): -13
 * Very good!
 *
 * ... (11 more lines omitted) ...
 *
 * Correct: 9  Incorrect: 1  Score: 90%
 * Congratulations, you are ready to go to the next level!
 * --- Next student ---
 * Enter the difficulty level (1-4): 1
 * Problem type: 1) addition  2) subtraction  3) multiplication  4) division  5) random mixture
 * Enter your choice: 1
 *
 * How much is 9 plus 3?
 * Your answer (-1 to quit): 12
 * Keep up the good work!
 *
 * How much is 8 plus 6?
 * Your answer (-1 to quit): -1
 */
