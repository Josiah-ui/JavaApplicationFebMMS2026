// Exercise 5.15 (Hypotenuse Calculations)
import java.util.Scanner;

public class Exercise5_15 {
    public static void main(String[] args) {
        // The three triangles of Fig. 5.14 (sides 3.0/4.0, 5.0/12.0 and 8.0/15.0)
        double[][] triangles = { {3.0, 4.0}, {5.0, 12.0}, {8.0, 15.0} };
        System.out.printf("%-12s%-8s%-8s%-12s%n", "Triangle", "Side 1", "Side 2", "Hypotenuse");
        for (int i = 0; i < triangles.length; i++) {
            System.out.printf("%-12d%-8.1f%-8.1f%-12.1f%n", i + 1,
                    triangles[i][0], triangles[i][1], hypotenuse(triangles[i][0], triangles[i][1]));
        }

        // Now the user's own triangle
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter side1: ");
        double side1 = input.nextDouble();
        System.out.print("Enter side2: ");
        double side2 = input.nextDouble();

        System.out.printf("The hypotenuse is %.2f%n", hypotenuse(side1, side2));
    }

    public static double hypotenuse(double side1, double side2) {
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Triangle    Side 1  Side 2  Hypotenuse  
 * 1           3.0     4.0     5.0         
 * 2           5.0     12.0    13.0        
 * 3           8.0     15.0    17.0        
 *
 * Enter side1: 6
 * Enter side2: 8
 * The hypotenuse is 10.00
 */
