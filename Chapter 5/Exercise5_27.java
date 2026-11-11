// Exercise 5.27 (Greatest Common Divisor)
import java.util.Scanner;

public class Exercise5_27 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter two integers separated by a space: ");
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.printf("The greatest common divisor of %d and %d is %d%n", a, b, gcd(a, b));
    }

    // Euclid's algorithm
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter two integers separated by a space: 48 180
 * The greatest common divisor of 48 and 180 is 12
 */
