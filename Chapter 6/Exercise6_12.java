// Exercise 6.12 (Duplicate Elimination)
import java.util.Scanner;

public class Exercise6_12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] unique = new int[5];   // smallest possible array: worst case is five different numbers
        int count = 0;               // number of unique values stored so far

        for (int i = 1; i <= 5; i++) {
            System.out.printf("Enter number %d (10-100): ", i);
            int number = input.nextInt();

            while (number < 10 || number > 100) {
                System.out.print("Invalid. The number must be between 10 and 100. Enter it again: ");
                number = input.nextInt();
            }

            boolean duplicate = false;
            for (int j = 0; j < count; j++) {
                if (unique[j] == number) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.printf("%d has already been entered.%n", number);
            } else {
                unique[count++] = number;
                System.out.printf("%d is not a duplicate.%n", number);
            }

            // display the complete set of unique values entered so far
            System.out.print("Unique values so far:");
            for (int j = 0; j < count; j++)
                System.out.print(" " + unique[j]);
            System.out.println();
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Enter number 1 (10-100): 22
 * 22 is not a duplicate.
 * Unique values so far: 22
 * Enter number 2 (10-100): 45
 * 45 is not a duplicate.
 * Unique values so far: 22 45
 * Enter number 3 (10-100): 22
 * 22 has already been entered.
 * Unique values so far: 22 45
 * Enter number 4 (10-100): 5
 * Invalid. The number must be between 10 and 100. Enter it again: 100
 * 100 is not a duplicate.
 * Unique values so far: 22 45 100
 * Enter number 5 (10-100): 45
 * 45 has already been entered.
 * Unique values so far: 22 45 100
 */
