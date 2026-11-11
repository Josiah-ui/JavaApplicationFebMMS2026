// Exercise 5.23 (Find the Minimum)
import java.util.Scanner;

public class Exercise5_23 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter three floating-point values separated by spaces: ");
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
        System.out.printf("The smallest value is %s%n", minimum3(a, b, c));
    }

    public static double minimum3(double x, double y, double z) {
        return Math.min(x, Math.min(y, z));
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter three floating-point values separated by spaces: 8.5 3.25 6.0
 * The smallest value is 3.25
 */
