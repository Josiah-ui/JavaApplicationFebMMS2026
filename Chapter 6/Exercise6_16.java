// Exercise 6.16 (Using the Enhanced for Statement)
// Usage:  java Exercise6_16 value1 value2 ...
public class Exercise6_16 {
    public static void main(String[] args) {
        double total = 0.0;

        for (String argument : args) {
            try {
                total += Double.parseDouble(argument);
            } catch (NumberFormatException e) {
                System.out.printf("Ignoring '%s' because it is not a number.%n", argument);
            }
        }

        System.out.printf("Sum of the command-line values: %.2f%n", total);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * [java Exercise6_16 1.5 2.5 3 10.25]
 * Sum of the command-line values: 17.25
 *
 * [java Exercise6_16 4 x 6]
 * Ignoring 'x' because it is not a number.
 * Sum of the command-line values: 10.00
 *
 * [java Exercise6_16]
 * Sum of the command-line values: 0.00
 */
