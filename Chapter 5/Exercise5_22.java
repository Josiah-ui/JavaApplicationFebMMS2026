// Exercise 5.22 (Temperature Conversions)
import java.util.Scanner;

public class Exercise5_22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("%n1) Fahrenheit to Celsius%n2) Celsius to Fahrenheit%n3) Exit%nEnter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                System.out.print("Enter a Fahrenheit temperature: ");
                int f = input.nextInt();
                System.out.printf("%d degrees Fahrenheit = %d degrees Celsius%n", f, celsius(f));
            } else if (choice == 2) {
                System.out.print("Enter a Celsius temperature: ");
                int c = input.nextInt();
                System.out.printf("%d degrees Celsius = %d degrees Fahrenheit%n", c, fahrenheit(c));
            } else if (choice != 3) {
                System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    // a) Celsius equivalent of a Fahrenheit temperature (rounded to an integer)
    public static int celsius(int fahrenheit) {
        return (int) Math.round(5.0 / 9.0 * (fahrenheit - 32));
    }

    // b) Fahrenheit equivalent of a Celsius temperature (rounded to an integer)
    public static int fahrenheit(int celsius) {
        return (int) Math.round(9.0 / 5.0 * celsius + 32);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * 1) Fahrenheit to Celsius
 * 2) Celsius to Fahrenheit
 * 3) Exit
 * Enter your choice: 1
 * Enter a Fahrenheit temperature: 98
 * 98 degrees Fahrenheit = 37 degrees Celsius
 *
 * 1) Fahrenheit to Celsius
 * 2) Celsius to Fahrenheit
 * 3) Exit
 * Enter your choice: 2
 * Enter a Celsius temperature: 37
 * 37 degrees Celsius = 99 degrees Fahrenheit
 *
 * 1) Fahrenheit to Celsius
 * 2) Celsius to Fahrenheit
 * 3) Exit
 * Enter your choice: 1
 * Enter a Fahrenheit temperature: 32
 * 32 degrees Fahrenheit = 0 degrees Celsius
 *
 * 1) Fahrenheit to Celsius
 * 2) Celsius to Fahrenheit
 * 3) Exit
 * Enter your choice: 3
 */
