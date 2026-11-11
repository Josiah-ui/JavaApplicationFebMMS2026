// Exercise 5.21 (Separating Digits)
import java.util.Scanner;

public class Exercise5_21 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer between 1 and 99999: ");
        int number = input.nextInt();

        if (number < 1 || number > 99999)
            System.out.println("The number must be between 1 and 99999.");
        else
            displayDigits(number);
    }

    // a) integer part of a / b
    public static int quotient(int a, int b) {
        return a / b;
    }

    // b) integer remainder of a / b
    public static int remainder(int a, int b) {
        return a % b;
    }

    // c) displays the digits separated by two spaces
    public static void displayDigits(int number) {
        int divisor = 10000;

        // skip leading zeros
        while (divisor > 1 && quotient(number, divisor) == 0)
            divisor = quotient(divisor, 10);

        StringBuilder output = new StringBuilder();
        while (divisor >= 1) {
            if (output.length() > 0)
                output.append("  ");
            output.append(quotient(number, divisor));
            number = remainder(number, divisor);
            divisor = quotient(divisor, 10);
        }
        System.out.println(output);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter an integer between 1 and 99999: 4562
 * 4  5  6  2
 */
