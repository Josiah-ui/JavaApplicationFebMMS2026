// Exercise 5.10 (Rounding Numbers)
import java.util.Scanner;

public class Exercise5_10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prompt = "%nEnter a number (type q to quit): ";

        System.out.printf(prompt);
        while (input.hasNextDouble()) {
            double number = input.nextDouble();
            System.out.printf("Original value:             %s%n", number);
            System.out.printf("Rounded to integer:         %.0f%n", roundToInteger(number));
            System.out.printf("Rounded to tenths:          %.1f%n", roundToTenths(number));
            System.out.printf("Rounded to hundredths:      %.2f%n", roundToHundredths(number));
            System.out.printf("Rounded to thousandths:     %.3f%n", roundToThousandths(number));
            System.out.printf(prompt);
        }
    }

    public static double roundToInteger(double number) {
        return Math.floor(number + 0.5);
    }

    public static double roundToTenths(double number) {
        return Math.floor(number * 10 + 0.5) / 10;
    }

    public static double roundToHundredths(double number) {
        return Math.floor(number * 100 + 0.5) / 100;
    }

    public static double roundToThousandths(double number) {
        return Math.floor(number * 1000 + 0.5) / 1000;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * Enter a number (type q to quit): 7.5555
 * Original value:             7.5555
 * Rounded to integer:         8
 * Rounded to tenths:          7.6
 * Rounded to hundredths:      7.56
 * Rounded to thousandths:     7.556
 *
 * Enter a number (type q to quit): -3.14159
 * Original value:             -3.14159
 * Rounded to integer:         -3
 * Rounded to tenths:          -3.1
 * Rounded to hundredths:      -3.14
 * Rounded to thousandths:     -3.142
 *
 * Enter a number (type q to quit): q
 */
