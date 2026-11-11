// Exercise 6.29 (Fibonacci Series)
import java.util.Scanner;

public class Exercise6_29 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // a) calculate the nth Fibonacci number for a value entered by the user
        System.out.print("Enter n (0 or more): ");
        int n = input.nextInt();
        System.out.printf("fibonacci(%d) = %d%n", n, fibonacci(n));

        // b) largest Fibonacci number that fits in an int
        int previous = 0;
        int current = 1;
        int index = 1;
        while (true) {
            int next = previous + current;
            if (next < current)      // the sum overflowed and wrapped around to a smaller (negative) value
                break;
            previous = current;
            current = next;
            index++;
        }
        System.out.printf("%nb) Largest Fibonacci number that can be stored in an int: fibonacci(%d) = %d%n", index, current);
        System.out.printf("   fibonacci(%d) overflows and comes out as %d%n", index + 1, fibonacci(index + 1));

        // c) the same search using double
        double a = 0.0;
        double b = 1.0;
        int doubleIndex = 1;
        while (!Double.isInfinite(a + b)) {
            double next = a + b;
            a = b;
            b = next;
            doubleIndex++;
        }
        System.out.printf("%nc) Largest Fibonacci number that can be stored in a double: fibonacci(%d) = %s%n", doubleIndex, b);
        System.out.printf("   fibonacci(%d) would be %s%n", doubleIndex + 1, a + b);
        System.out.printf("   fibonacciDouble(%d) = %s%n", n, fibonacciDouble(n));

        // doubles keep only about 15-16 significant digits, so large results are no longer exact
        long x = 0, y = 1;
        int firstInexact = -1;
        for (int i = 2; i <= 92 && firstInexact == -1; i++) {
            long next = x + y;
            x = y;
            y = next;
            if ((double) y != fibonacciDouble(i) || (long) fibonacciDouble(i) != y)
                firstInexact = i;
        }
        System.out.printf("   The double result stops matching the exact value at fibonacci(%d)%n", firstInexact);
    }

    // a) iterative version using int
    public static int fibonacci(int n) {
        if (n < 2)
            return n;
        int previous = 0;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    // c) the same method using double
    public static double fibonacciDouble(int n) {
        if (n < 2)
            return n;
        double previous = 0;
        double current = 1;
        for (int i = 2; i <= n; i++) {
            double next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Enter n (0 or more): 10
 * fibonacci(10) = 55
 *
 * b) Largest Fibonacci number that can be stored in an int: fibonacci(46) = 1836311903
 *    fibonacci(47) overflows and comes out as -1323752223
 *
 * c) Largest Fibonacci number that can be stored in a double: fibonacci(1476) = 1.3069892237633987E308
 *    fibonacci(1477) would be Infinity
 *    fibonacciDouble(10) = 55.0
 *    The double result stops matching the exact value at fibonacci(79)
 */
