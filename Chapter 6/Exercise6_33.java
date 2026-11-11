// Exercise 6.33 (Polling)
import java.util.Scanner;

public class Exercise6_33 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] topics = {
            "Climate change",
            "Affordable health care",
            "Quality public education",
            "Hunger and poverty",
            "Clean drinking water"
        };
        int[][] responses = new int[5][10];   // responses[topic][rating - 1] = number of times that rating was given

        int respondents = 0;
        char another;
        do {
            respondents++;
            System.out.printf("%nRespondent %d - rate each issue from 1 (least important) to 10 (most important)%n", respondents);
            for (int t = 0; t < topics.length; t++) {
                System.out.printf("  %s: ", topics[t]);
                int rating = scan.nextInt();
                while (rating < 1 || rating > 10) {
                    System.out.print("  Rating must be from 1 to 10. Try again: ");
                    rating = scan.nextInt();
                }
                responses[t][rating - 1]++;
            }
            System.out.print("Another respondent (y or n)? ");
            another = scan.next().toLowerCase().charAt(0);
        } while (another == 'y');

        // a) and b) table of rating counts with the average to the right of each row
        System.out.printf("%n%-26s", "Topic");
        for (int rating = 1; rating <= 10; rating++)
            System.out.printf("%4d", rating);
        System.out.printf("%10s%n", "Average");

        int[] totals = new int[topics.length];
        for (int t = 0; t < topics.length; t++) {
            int count = 0;
            for (int r = 0; r < 10; r++) {
                count += responses[t][r];
                totals[t] += responses[t][r] * (r + 1);
            }
            System.out.printf("%-26s", topics[t]);
            for (int r = 0; r < 10; r++)
                System.out.printf("%4d", responses[t][r]);
            System.out.printf("%10.2f%n", count == 0 ? 0.0 : (double) totals[t] / count);
        }

        // c) and d) highest and lowest point totals (all tied issues are listed)
        int highest = totals[0];
        int lowest = totals[0];
        for (int total : totals) {
            highest = Math.max(highest, total);
            lowest = Math.min(lowest, total);
        }

        System.out.println();
        for (int t = 0; t < topics.length; t++)
            if (totals[t] == highest)
                System.out.printf("Highest point total: %s with %d points%n", topics[t], highest);
        for (int t = 0; t < topics.length; t++)
            if (totals[t] == lowest)
                System.out.printf("Lowest point total:  %s with %d points%n", topics[t], lowest);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 *
 * Respondent 1 - rate each issue from 1 (least important) to 10 (most important)
 *   Climate change: 10
 *   Affordable health care: 8
 *   Quality public education: 7
 *   Hunger and poverty: 9
 *   Clean drinking water: 6
 * Another respondent (y or n)? y
 *
 * Respondent 2 - rate each issue from 1 (least important) to 10 (most important)
 *   Climate change: 9
 *   Affordable health care: 7
 *   Quality public education: 8
 *   Hunger and poverty: 10
 *   Clean drinking water: 5
 * Another respondent (y or n)? y
 *
 * Respondent 3 - rate each issue from 1 (least important) to 10 (most important)
 *   Climate change: 8
 *   Affordable health care: 9
 *   Quality public education: 6
 *   Hunger and poverty: 7
 *   Clean drinking water: 9
 * Another respondent (y or n)? n
 *
 * Topic                        1   2   3   4   5   6   7   8   9  10   Average
 * Climate change               0   0   0   0   0   0   0   1   1   1      9.00
 * Affordable health care       0   0   0   0   0   0   1   1   1   0      8.00
 * Quality public education     0   0   0   0   0   1   1   1   0   0      7.00
 * Hunger and poverty           0   0   0   0   0   0   1   0   1   1      8.67
 * Clean drinking water         0   0   0   0   1   1   0   0   1   0      6.67
 *
 * Highest point total: Climate change with 27 points
 * Lowest point total:  Clean drinking water with 20 points
 */
