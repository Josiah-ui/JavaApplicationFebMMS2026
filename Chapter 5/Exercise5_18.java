// Exercise 5.18 (Displaying a Square of Asterisks)
import java.util.Scanner;

public class Exercise5_18 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the side of the square: ");
        int side = input.nextInt();
        squareOfAsterisks(side);
    }

    public static void squareOfAsterisks(int side) {
        for (int row = 1; row <= side; row++) {
            for (int column = 1; column <= side; column++)
                System.out.print("*");
            System.out.println();
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter the side of the square: 4
 * ****
 * ****
 * ****
 * ****
 */
