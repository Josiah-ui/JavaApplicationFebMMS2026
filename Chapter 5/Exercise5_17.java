// Exercise 5.17 (Even or Odd)
import java.util.Scanner;

public class Exercise5_17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prompt = "Enter an integer (type q to quit): ";

        System.out.print(prompt);
        while (input.hasNextInt()) {
            int number = input.nextInt();
            if (isEven(number))
                System.out.printf("%d is even%n", number);
            else
                System.out.printf("%d is odd%n", number);
            System.out.print(prompt);
        }
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter an integer (type q to quit): 4
 * 4 is even
 * Enter an integer (type q to quit): 7
 * 7 is odd
 * Enter an integer (type q to quit): 0
 * 0 is even
 * Enter an integer (type q to quit): -3
 * -3 is odd
 * Enter an integer (type q to quit): q
 */
