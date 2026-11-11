// Exercise 5.24 (Perfect Numbers)
public class Exercise5_24 {
    public static void main(String[] args) {
        System.out.println("Perfect numbers between 1 and 1000:");
        displayPerfectNumbers(1000);

        System.out.println("\nChallenge - perfect numbers between 1 and 10000:");
        displayPerfectNumbers(10000);
    }

    public static void displayPerfectNumbers(int limit) {
        for (int number = 1; number <= limit; number++) {
            if (isPerfect(number)) {
                StringBuilder factors = new StringBuilder();
                for (int i = 1; i < number; i++) {
                    if (number % i == 0) {
                        if (factors.length() > 0)
                            factors.append(" + ");
                        factors.append(i);
                    }
                }
                System.out.printf("%d = %s%n", number, factors);
            }
        }
    }

    // true if the factors of number (including 1, excluding number itself) sum to number
    public static boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0)
                sum += i;
        }
        return number > 1 && sum == number;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Perfect numbers between 1 and 1000:
 * 6 = 1 + 2 + 3
 * 28 = 1 + 2 + 4 + 7 + 14
 * 496 = 1 + 2 + 4 + 8 + 16 + 31 + 62 + 124 + 248
 *
 * Challenge - perfect numbers between 1 and 10000:
 * 6 = 1 + 2 + 3
 * 28 = 1 + 2 + 4 + 7 + 14
 * 496 = 1 + 2 + 4 + 8 + 16 + 31 + 62 + 124 + 248
 * 8128 = 1 + 2 + 4 + 8 + 16 + 32 + 64 + 127 + 254 + 508 + 1016 + 2032 + 4064
 */
