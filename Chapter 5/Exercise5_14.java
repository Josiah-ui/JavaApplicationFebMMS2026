// Exercise 5.14 (Exponentiation)
import java.util.Scanner;

public class Exercise5_14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the base (an integer): ");
        int base = input.nextInt();

        System.out.print("Enter the exponent (a positive integer): ");
        int exponent = input.nextInt();

        while (exponent < 1) {
            System.out.print("The exponent must be positive. Enter the exponent again: ");
            exponent = input.nextInt();
        }

        System.out.printf("%d raised to the power %d is %d%n", base, exponent, integerPower(base, exponent));
    }

    // returns base raised to the power exponent (no Math methods used)
    public static int integerPower(int base, int exponent) {
        int result = 1;
        for (int i = 1; i <= exponent; i++)
            result *= base;
        return result;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter the base (an integer): 3
 * Enter the exponent (a positive integer): 4
 * 3 raised to the power 4 is 81
 */
