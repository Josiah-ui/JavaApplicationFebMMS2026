// Exercise 6.10 (Sales Commissions)
import java.util.Scanner;

public class Exercise6_10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // counters[0] = $200-299, counters[1] = $300-399, ... counters[7] = $900-999, counters[8] = $1000 and over
        int[] counters = new int[9];

        System.out.print("Enter a salesperson's gross weekly sales (negative number to finish): ");
        double sales = input.nextDouble();

        while (sales >= 0) {
            // $200 plus 9% of gross sales, truncated to an integer (1e-9 guards against floating-point error)
            int salary = (int) (200 + 0.09 * sales + 1e-9);
            System.out.printf("Salary: $%d%n", salary);

            int index = Math.min(salary / 100, 10) - 2;   // 200-299 -> 0, ..., 1000+ -> 8
            counters[index]++;

            System.out.print("Enter a salesperson's gross weekly sales (negative number to finish): ");
            sales = input.nextDouble();
        }

        System.out.printf("%nEmployees by salary range:%n");
        System.out.printf("%-17s%s%n", "Range", "Number");
        for (int i = 0; i < counters.length - 1; i++)
            System.out.printf("%-17s%d%n", "$" + (i + 2) * 100 + "-$" + ((i + 2) * 100 + 99), counters[i]);
        System.out.printf("%-17s%d%n", "$1,000 and over", counters[8]);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Enter a salesperson's gross weekly sales (negative number to finish): 5000
 * Salary: $650
 * Enter a salesperson's gross weekly sales (negative number to finish): 3000
 * Salary: $470
 * Enter a salesperson's gross weekly sales (negative number to finish): 1200
 * Salary: $308
 * Enter a salesperson's gross weekly sales (negative number to finish): 0
 * Salary: $200
 * Enter a salesperson's gross weekly sales (negative number to finish): 12000
 * Salary: $1280
 * Enter a salesperson's gross weekly sales (negative number to finish): 8000
 * Salary: $920
 * Enter a salesperson's gross weekly sales (negative number to finish): 2500
 * Salary: $425
 * Enter a salesperson's gross weekly sales (negative number to finish): 6000
 * Salary: $740
 * Enter a salesperson's gross weekly sales (negative number to finish): -1
 *
 * Employees by salary range:
 * Range            Number
 * $200-$299        1
 * $300-$399        1
 * $400-$499        2
 * $500-$599        0
 * $600-$699        1
 * $700-$799        1
 * $800-$899        0
 * $900-$999        1
 * $1,000 and over  1
 */
