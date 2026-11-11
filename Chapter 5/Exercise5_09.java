// Exercise 5.9 (Rounding Numbers)
import java.util.Scanner;

public class Exercise5_09 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prompt = "Enter a number (type q to quit): ";

        System.out.print(prompt);
        while (input.hasNextDouble()) {
            double x = input.nextDouble();
            double y = Math.floor(x + 0.5);   // rounds x to the nearest integer
            System.out.printf("Original number: %s   Rounded number: %.0f%n", x, y);
            System.out.print(prompt);
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter a number (type q to quit): 7.5
 * Original number: 7.5   Rounded number: 8
 * Enter a number (type q to quit): -3.4
 * Original number: -3.4   Rounded number: -3
 * Enter a number (type q to quit): 12.5
 * Original number: 12.5   Rounded number: 13
 * Enter a number (type q to quit): 2.3
 * Original number: 2.3   Rounded number: 2
 * Enter a number (type q to quit): q
 */
