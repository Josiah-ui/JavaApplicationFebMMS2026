// Exercise 6.15 (Command-Line Arguments)
// Fig. 6.2 (InitArray) rewritten so the array size comes from the first command-line argument.
// Usage:  java Exercise6_15 [arraySize]      (default size is 10)
public class Exercise6_15 {
    public static void main(String[] args) {
        int arrayLength = 10;   // default size

        if (args.length >= 1) {
            try {
                arrayLength = Integer.parseInt(args[0]);
                if (arrayLength < 0) {
                    System.out.println("The array size cannot be negative; using the default size of 10.");
                    arrayLength = 10;
                }
            } catch (NumberFormatException e) {
                System.out.printf("'%s' is not a valid integer; using the default size of 10.%n", args[0]);
            }
        }

        int[] array = new int[arrayLength];   // elements are initialized to 0

        System.out.printf("%s%8s%n", "Index", "Value");
        for (int counter = 0; counter < array.length; counter++)
            System.out.printf("%5d%8d%n", counter, array[counter]);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * [java Exercise6_15]
 * Index   Value
 *     0       0
 *     1       0
 *     2       0
 *     3       0
 *     4       0
 *     5       0
 *     6       0
 *     7       0
 *     8       0
 *     9       0
 *
 * [java Exercise6_15 5]
 * Index   Value
 *     0       0
 *     1       0
 *     2       0
 *     3       0
 *     4       0
 *
 * [java Exercise6_15 abc]
 * 'abc' is not a valid integer; using the default size of 10.
 * Index   Value
 *     0       0
 *     1       0
 *     2       0
 *     3       0
 *     4       0
 *     5       0
 *     6       0
 *     7       0
 *     8       0
 *     9       0
 */
