// Exercise 6.19 (Total Sales)
import java.util.Scanner;

public class Exercise6_19 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] sales = new double[5][4];   // rows = products 1-5, columns = salespeople 1-4

        System.out.println("Enter one sales slip per line: salesperson(1-4) product(1-5) dollar value");
        System.out.println("Enter -1 as the salesperson number to finish.");

        System.out.print("Slip: ");
        int person = input.nextInt();
        while (person != -1) {
            int product = input.nextInt();
            double value = input.nextDouble();

            if (person < 1 || person > 4 || product < 1 || product > 5 || value < 0)
                System.out.println("Invalid slip ignored (salesperson 1-4, product 1-5, value >= 0).");
            else
                sales[product - 1][person - 1] += value;

            System.out.print("Slip: ");
            person = input.nextInt();
        }

        // display the table with row and column cross-totals
        System.out.printf("%n%-10s", "Product");
        for (int p = 1; p <= 4; p++)
            System.out.printf("%14s", "Salesperson " + p);
        System.out.printf("%14s%n", "Total");

        double[] columnTotals = new double[4];
        double grandTotal = 0;

        for (int product = 0; product < sales.length; product++) {
            double rowTotal = 0;
            System.out.printf("%-10d", product + 1);
            for (int p = 0; p < sales[product].length; p++) {
                System.out.printf("%14.2f", sales[product][p]);
                rowTotal += sales[product][p];
                columnTotals[p] += sales[product][p];
            }
            System.out.printf("%14.2f%n", rowTotal);
            grandTotal += rowTotal;
        }

        System.out.printf("%-10s", "Total");
        for (double total : columnTotals)
            System.out.printf("%14.2f", total);
        System.out.printf("%14.2f%n", grandTotal);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Enter one sales slip per line: salesperson(1-4) product(1-5) dollar value
 * Enter -1 as the salesperson number to finish.
 * Slip: 1 1 150.50
 * Slip: 1 3 200
 * Slip: 2 1 75.25
 * Slip: 2 4 310
 * Slip: 3 2 120
 * Slip: 3 5 99.99
 * Slip: 4 1 410
 * Slip: 1 1 49.50
 * Slip: 4 5 15
 * Slip: 5 1 20
 * Invalid slip ignored (salesperson 1-4, product 1-5, value >= 0).
 * Slip: -1
 *
 * Product    Salesperson 1 Salesperson 2 Salesperson 3 Salesperson 4         Total
 * 1                 200.00         75.25          0.00        410.00        685.25
 * 2                   0.00          0.00        120.00          0.00        120.00
 * 3                 200.00          0.00          0.00          0.00        200.00
 * 4                   0.00        310.00          0.00          0.00        310.00
 * 5                   0.00          0.00         99.99         15.00        114.99
 * Total             400.00        385.25        219.99        425.00       1430.24
 */
