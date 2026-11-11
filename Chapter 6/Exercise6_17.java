// Exercise 6.17 (Dice Rolling)
import java.util.Random;

public class Exercise6_17 {
    public static void main(String[] args) {
        Random randomNumbers = new Random();
        final int ROLLS = 36_000_000;
        int[] frequency = new int[13];   // frequency[sum] for sums 2 through 12

        for (int roll = 1; roll <= ROLLS; roll++) {
            int die1 = 1 + randomNumbers.nextInt(6);   // first die
            int die2 = 1 + randomNumbers.nextInt(6);   // second die
            frequency[die1 + die2]++;
        }

        System.out.printf("Rolled two dice %,d times%n%n", ROLLS);
        System.out.printf("%-6s%-14s%-12s%-12s%n", "Sum", "Frequency", "Percent", "Expected %");
        for (int sum = 2; sum <= 12; sum++) {
            int ways = 6 - Math.abs(sum - 7);   // number of combinations that make this sum
            System.out.printf("%-6d%-14d%-12.3f%-12.3f%n", sum, frequency[sum],
                    100.0 * frequency[sum] / ROLLS, 100.0 * ways / 36);
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Rolled two dice 36,000,000 times
 *
 * Sum   Frequency     Percent     Expected %  
 * 2     999222        2.776       2.778       
 * 3     2001081       5.559       5.556       
 * 4     2999985       8.333       8.333       
 * 5     3999477       11.110      11.111      
 * 6     4999649       13.888      13.889      
 * 7     5999132       16.664      16.667      
 * 8     4999427       13.887      13.889      
 * 9     3998749       11.108      11.111      
 * 10    2999941       8.333       8.333       
 * 11    2002817       5.563       5.556       
 * 12    1000520       2.779       2.778       
 */
