// Exercise 6.14 (Variable-Length Argument List)
public class Exercise6_14 {
    public static void main(String[] args) {
        System.out.printf("product(2, 3)           = %d%n", product(2, 3));
        System.out.printf("product(5)              = %d%n", product(5));
        System.out.printf("product(1, 2, 3, 4)     = %d%n", product(1, 2, 3, 4));
        System.out.printf("product(2, 4, 6, 8, 10) = %d%n", product(2, 4, 6, 8, 10));
        System.out.printf("product(7, 0, 9)        = %d%n", product(7, 0, 9));
        System.out.printf("product()               = %d  (no arguments: empty product is 1)%n", product());
    }

    // calculates the product of any number of integers
    public static int product(int... numbers) {
        int result = 1;
        for (int number : numbers)
            result *= number;
        return result;
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * product(2, 3)           = 6
 * product(5)              = 5
 * product(1, 2, 3, 4)     = 24
 * product(2, 4, 6, 8, 10) = 3840
 * product(7, 0, 9)        = 0
 * product()               = 1  (no arguments: empty product is 1)
 */
