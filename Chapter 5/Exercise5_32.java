// Exercise 5.32 (Distance Between Points)
import java.util.Scanner;

public class Exercise5_32 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter x1: ");
        double x1 = input.nextDouble();
        System.out.print("Enter y1: ");
        double y1 = input.nextDouble();
        System.out.print("Enter x2: ");
        double x2 = input.nextDouble();
        System.out.print("Enter y2: ");
        double y2 = input.nextDouble();

        System.out.printf("The distance between (%.1f, %.1f) and (%.1f, %.1f) is %.4f%n",
                x1, y1, x2, y2, distance(x1, y1, x2, y2));
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter x1: 0
 * Enter y1: 0
 * Enter x2: 3
 * Enter y2: 4
 * The distance between (0.0, 0.0) and (3.0, 4.0) is 5.0000
 */
