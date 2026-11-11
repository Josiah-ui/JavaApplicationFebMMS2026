// Exercise 5.19 (Displaying a Square of Any Character)
import java.util.Scanner;

public class Exercise5_19 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the side of the square: ");
        int side = input.nextInt();
        System.out.print("Enter the fill character: ");
        char fill = input.next().charAt(0);
        squareOfAsterisks(side, fill);
    }

    public static void squareOfAsterisks(int side, char fillCharacter) {
        for (int row = 1; row <= side; row++) {
            for (int column = 1; column <= side; column++)
                System.out.print(fillCharacter);
            System.out.println();
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter the side of the square: 5
 * Enter the fill character: #
 * #####
 * #####
 * #####
 * #####
 * #####
 */
