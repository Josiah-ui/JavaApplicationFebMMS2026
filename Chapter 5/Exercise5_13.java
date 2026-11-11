// Exercise 5.13 - random numbers from given sets
import java.security.SecureRandom;

public class Exercise5_13 {
    public static void main(String[] args) {
        SecureRandom randomNumbers = new SecureRandom();

        // a) 2, 4, 6, 8, 10
        System.out.println("a) " + (2 + 2 * randomNumbers.nextInt(5)));

        // b) 3, 5, 7, 9, 11
        System.out.println("b) " + (3 + 2 * randomNumbers.nextInt(5)));

        // c) 6, 10, 14, 18, 22
        System.out.println("c) " + (6 + 4 * randomNumbers.nextInt(5)));
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * a) 2
 * b) 3
 * c) 14
 */
