// Exercise 7.22 (Target-Heart-Rate Calculator)
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Exercise7_22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = input.next();
        System.out.print("Enter last name: ");
        String lastName = input.next();

        int month, day, year;
        while (true) {
            System.out.print("Enter month of birth (1-12): ");
            month = input.nextInt();
            System.out.print("Enter day of birth: ");
            day = input.nextInt();
            System.out.print("Enter year of birth: ");
            year = input.nextInt();

            try {
                LocalDate birthday = LocalDate.of(year, month, day);
                if (birthday.isAfter(LocalDate.now()))
                    System.out.println("That date is in the future. Please try again.");
                else
                    break;
            } catch (DateTimeException e) {
                System.out.println("That is not a valid date. Please try again.");
            }
        }

        HeartRates person = new HeartRates(firstName, lastName, month, day, year);

        System.out.printf("%nName:          %s %s%n", person.getFirstName(), person.getLastName());
        System.out.printf("Date of birth: %d/%d/%d%n", person.getMonth(), person.getDay(), person.getYear());
        System.out.printf("Age:           %d years%n", person.getAge());
        System.out.printf("Maximum heart rate: %d beats per minute%n", person.getMaximumHeartRate());

        double[] target = person.getTargetHeartRate();
        System.out.printf("Target heart rate:  %.0f to %.0f beats per minute (50%% to 85%% of the maximum)%n",
                target[0], target[1]);

        System.out.println("\nThese formulas are estimates provided by the American Heart Association.");
        System.out.println("Consult a physician or qualified healthcare professional before beginning");
        System.out.println("or modifying an exercise program.");
    }
}

class HeartRates {
    private String firstName;
    private String lastName;
    private int month;   // month of birth (1-12)
    private int day;     // day of birth
    private int year;    // year of birth

    public HeartRates(String firstName, String lastName, int month, int day, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    // age in whole years, taking the month and day of the birthday into account
    public int getAge() {
        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
    }

    // maximum heart rate = 220 minus age
    public int getMaximumHeartRate() {
        return 220 - getAge();
    }

    // target heart rate range: 50% to 85% of the maximum; returns {lowest, highest}
    public double[] getTargetHeartRate() {
        int maximum = getMaximumHeartRate();
        return new double[] {0.50 * maximum, 0.85 * maximum};
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Enter first name: Maria
 * Enter last name: Lopez
 * Enter month of birth (1-12): 3
 * Enter day of birth: 14
 * Enter year of birth: 1990
 *
 * Name:          Maria Lopez
 * Date of birth: 3/14/1990
 * Age:           36 years
 * Maximum heart rate: 184 beats per minute
 * Target heart rate:  92 to 156 beats per minute (50% to 85% of the maximum)
 *
 * These formulas are estimates provided by the American Heart Association.
 * Consult a physician or qualified healthcare professional before beginning
 * or modifying an exercise program.
 */
