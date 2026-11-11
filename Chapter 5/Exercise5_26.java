// Exercise 5.26 (Reversing Digits)
import java.util.Scanner;

public class Exercise5_26 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int number = input.nextInt();
        System.out.printf("%d reversed is %d%n", number, reverse(number));
    }

    public static int reverse(int number) {
        int remaining = Math.abs(number);
        int reversed = 0;
        while (remaining > 0) {
            reversed = reversed * 10 + remaining % 10;
            remaining /= 10;
        }
        return number < 0 ? -reversed : reversed;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter an integer: 7631
 * 7631 reversed is 1367
 */
