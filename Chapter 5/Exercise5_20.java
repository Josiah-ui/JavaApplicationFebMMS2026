// Exercise 5.20 (Circle Area)
import java.util.Scanner;

public class Exercise5_20 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the radius of the circle: ");
        double radius = input.nextDouble();
        System.out.printf("The area of the circle is %.2f%n", circleArea(radius));
    }

    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter the radius of the circle: 5
 * The area of the circle is 78.54
 */
