// Exercise 5.28 (Quality Points)
import java.util.Scanner;

public class Exercise5_28 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String prompt = "Enter a student's average (type q to quit): ";

        System.out.print(prompt);
        while (input.hasNextInt()) {
            int average = input.nextInt();
            System.out.printf("Average %d earns %d quality point(s)%n", average, qualityPoints(average));
            System.out.print(prompt);
        }
    }

    public static int qualityPoints(int average) {
        if (average >= 90)
            return 4;
        else if (average >= 80)
            return 3;
        else if (average >= 70)
            return 2;
        else if (average >= 60)
            return 1;
        else
            return 0;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter a student's average (type q to quit): 95
 * Average 95 earns 4 quality point(s)
 * Enter a student's average (type q to quit): 85
 * Average 85 earns 3 quality point(s)
 * Enter a student's average (type q to quit): 72
 * Average 72 earns 2 quality point(s)
 * Enter a student's average (type q to quit): 65
 * Average 65 earns 1 quality point(s)
 * Enter a student's average (type q to quit): 40
 * Average 40 earns 0 quality point(s)
 * Enter a student's average (type q to quit): q
 */
