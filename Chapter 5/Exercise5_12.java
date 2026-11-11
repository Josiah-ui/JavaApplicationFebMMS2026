// Exercise 5.12 - random integers in various ranges
import java.security.SecureRandom;

public class Exercise5_12 {
    public static void main(String[] args) {
        SecureRandom randomNumbers = new SecureRandom();
        int n;

        n = 1 + randomNumbers.nextInt(2);        // a) 1 <= n <= 2
        System.out.println("a) 1 <= n <= 2:       n = " + n);

        n = 1 + randomNumbers.nextInt(100);      // b) 1 <= n <= 100
        System.out.println("b) 1 <= n <= 100:     n = " + n);

        n = randomNumbers.nextInt(10);           // c) 0 <= n <= 9
        System.out.println("c) 0 <= n <= 9:       n = " + n);

        n = 1000 + randomNumbers.nextInt(113);   // d) 1000 <= n <= 1112
        System.out.println("d) 1000 <= n <= 1112: n = " + n);

        n = -1 + randomNumbers.nextInt(3);       // e) -1 <= n <= 1
        System.out.println("e) -1 <= n <= 1:      n = " + n);

        n = -3 + randomNumbers.nextInt(15);      // f) -3 <= n <= 11
        System.out.println("f) -3 <= n <= 11:     n = " + n);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * a) 1 <= n <= 2:       n = 2
 * b) 1 <= n <= 100:     n = 33
 * c) 0 <= n <= 9:       n = 4
 * d) 1000 <= n <= 1112: n = 1105
 * e) -1 <= n <= 1:      n = -1
 * f) -3 <= n <= 11:     n = 1
 */
