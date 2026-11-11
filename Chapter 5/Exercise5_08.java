// Exercise 5.8 (Parking Charges)
import java.util.Scanner;

public class Exercise5_08 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totalReceipts = 0.0;
        int car = 1;

        System.out.printf("Enter hours parked for car %d (negative number to quit): ", car);
        double hours = input.nextDouble();

        while (hours >= 0) {
            if (hours > 24) {
                System.out.println("No car parks for longer than 24 hours. Please try again.");
            } else {
                double charge = calculateCharges(hours);
                totalReceipts += charge;
                System.out.printf("Car %d: %.1f hours -> charge $%.2f | running total $%.2f%n",
                        car, hours, charge, totalReceipts);
                car++;
            }
            System.out.printf("Enter hours parked for car %d (negative number to quit): ", car);
            hours = input.nextDouble();
        }

        System.out.printf("%nTotal receipts for yesterday: $%.2f%n", totalReceipts);
    }

    // $2.00 minimum for up to 3 hours, $0.50 for each extra hour (or part of an hour), $10.00 maximum
    public static double calculateCharges(double hours) {
        final double MINIMUM_FEE = 2.00;
        final double EXTRA_HOUR_RATE = 0.50;
        final double MAXIMUM_FEE = 10.00;
        final double MINIMUM_HOURS = 3.0;

        if (hours <= MINIMUM_HOURS)
            return MINIMUM_FEE;

        double charge = MINIMUM_FEE + EXTRA_HOUR_RATE * Math.ceil(hours - MINIMUM_HOURS);
        return Math.min(charge, MAXIMUM_FEE);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Enter hours parked for car 1 (negative number to quit): 3
 * Car 1: 3.0 hours -> charge $2.00 | running total $2.00
 * Enter hours parked for car 2 (negative number to quit): 5.5
 * Car 2: 5.5 hours -> charge $3.50 | running total $5.50
 * Enter hours parked for car 3 (negative number to quit): 12
 * Car 3: 12.0 hours -> charge $6.50 | running total $12.00
 * Enter hours parked for car 4 (negative number to quit): 24
 * Car 4: 24.0 hours -> charge $10.00 | running total $22.00
 * Enter hours parked for car 5 (negative number to quit): 30
 * No car parks for longer than 24 hours. Please try again.
 * Enter hours parked for car 5 (negative number to quit): -1
 *
 * Total receipts for yesterday: $22.00
 */
