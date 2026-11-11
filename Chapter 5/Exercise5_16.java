// Exercise 5.16 (Multiples)
import java.util.Scanner;

public class Exercise5_16 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prompt = "Enter a pair of integers (type q to quit): ";

        System.out.print(prompt);
        while (input.hasNextInt()) {
            int first = input.nextInt();
            if (!input.hasNextInt())
                break;
            int second = input.nextInt();

            if (first == 0)
                System.out.println("The first integer cannot be 0.");
            else if (isMultiple(first, second))
                System.out.printf("%d is a multiple of %d%n", second, first);
            else
                System.out.printf("%d is NOT a multiple of %d%n", second, first);

            System.out.print(prompt);
        }
    }

    // true if second is a multiple of first
    public static boolean isMultiple(int first, int second) {
        return second % first == 0;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter a pair of integers (type q to quit): 3 12
 * 12 is a multiple of 3
 * Enter a pair of integers (type q to quit): 5 11
 * 11 is NOT a multiple of 5
 * Enter a pair of integers (type q to quit): 4 16
 * 16 is a multiple of 4
 * Enter a pair of integers (type q to quit): 0 5
 * The first integer cannot be 0.
 * Enter a pair of integers (type q to quit): q
 */
