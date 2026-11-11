// Exercise 5.29 (Coin Tossing)
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_29 {
    private enum Coin { HEADS, TAILS }

    private static final SecureRandom randomNumbers = new SecureRandom();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int heads = 0;
        int tails = 0;
        int choice;

        do {
            System.out.printf("%n1) Toss Coin%n2) Exit%nEnter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                Coin result = flip();
                if (result == Coin.HEADS)
                    heads++;
                else
                    tails++;
                System.out.printf("The coin landed on %s (heads: %d, tails: %d)%n", result, heads, tails);
            } else if (choice != 2) {
                System.out.println("Invalid choice.");
            }
        } while (choice != 2);

        int total = heads + tails;
        System.out.printf("%nTotal tosses: %d%n", total);
        if (total > 0) {
            System.out.printf("HEADS: %d (%.1f%%)%n", heads, 100.0 * heads / total);
            System.out.printf("TAILS: %d (%.1f%%)%n", tails, 100.0 * tails / total);
        }
    }

    // tosses the coin
    public static Coin flip() {
        return Coin.values()[randomNumbers.nextInt(2)];
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on TAILS (heads: 0, tails: 1)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on TAILS (heads: 0, tails: 2)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on HEADS (heads: 1, tails: 2)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on HEADS (heads: 2, tails: 2)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on TAILS (heads: 2, tails: 3)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on HEADS (heads: 3, tails: 3)
 * ... (14 more lines omitted) ...
 * The coin landed on TAILS (heads: 3, tails: 6)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 1
 * The coin landed on HEADS (heads: 4, tails: 6)
 *
 * 1) Toss Coin
 * 2) Exit
 * Enter your choice: 2
 *
 * Total tosses: 10
 * HEADS: 4 (40.0%)
 * TAILS: 6 (60.0%)
 */
