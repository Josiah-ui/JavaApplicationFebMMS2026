// Exercise 6.18 (Airline Reservations System)
import java.util.Scanner;

public class Exercise6_18 {
    private static final int FIRST_CLASS = 1;
    private static final int ECONOMY = 2;

    // seats[0]..seats[4] = seats 1-5 (first class), seats[5]..seats[9] = seats 6-10 (economy)
    private static boolean[] seats = new boolean[10];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            if (planeIsFull()) {
                System.out.println("\nSorry, the plane is completely booked. Next flight leaves in 3 hours.");
                break;
            }

            System.out.printf("%nPlease type 1 for First Class%nPlease type 2 for Economy%nType 0 to stop: ");
            int choice = input.nextInt();

            if (choice == 0) {
                break;
            } else if (choice != FIRST_CLASS && choice != ECONOMY) {
                System.out.println("Invalid choice.");
                continue;
            }

            int seat = assignSeat(choice);
            if (seat != 0) {
                printBoardingPass(seat);
            } else {
                int other = (choice == FIRST_CLASS) ? ECONOMY : FIRST_CLASS;
                String thisName = (choice == FIRST_CLASS) ? "First Class" : "Economy";
                String otherName = (other == FIRST_CLASS) ? "First Class" : "Economy";

                System.out.printf("%s is full. Is it acceptable to be placed in %s (y or n)? ", thisName, otherName);
                char answer = input.next().toLowerCase().charAt(0);
                if (answer == 'y')
                    printBoardingPass(assignSeat(other));
                else
                    System.out.println("Next flight leaves in 3 hours.");
            }
        }
    }

    // assigns the first empty seat in the section; returns the seat number (1-10) or 0 if the section is full
    public static int assignSeat(int section) {
        int start = (section == FIRST_CLASS) ? 0 : 5;
        int end = (section == FIRST_CLASS) ? 5 : 10;

        for (int i = start; i < end; i++) {
            if (!seats[i]) {
                seats[i] = true;
                return i + 1;
            }
        }
        return 0;
    }

    public static boolean planeIsFull() {
        for (boolean taken : seats)
            if (!taken)
                return false;
        return true;
    }

    public static void printBoardingPass(int seat) {
        System.out.println("-------- BOARDING PASS --------");
        System.out.printf("Seat number: %d%n", seat);
        System.out.printf("Section:     %s%n", seat <= 5 ? "First Class" : "Economy");
        System.out.println("-------------------------------");
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * -------- BOARDING PASS --------
 * Seat number: 1
 * Section:     First Class
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * -------- BOARDING PASS --------
 * Seat number: 2
 * Section:     First Class
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * -------- BOARDING PASS --------
 * Seat number: 3
 * Section:     First Class
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * -------- BOARDING PASS --------
 * Seat number: 4
 * Section:     First Class
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * -------- BOARDING PASS --------
 * Seat number: 5
 * Section:     First Class
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * First Class is full. Is it acceptable to be placed in Economy (y or n)? n
 * Next flight leaves in 3 hours.
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 1
 * First Class is full. Is it acceptable to be placed in Economy (y or n)? y
 * -------- BOARDING PASS --------
 * Seat number: 6
 * Section:     Economy
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 2
 * -------- BOARDING PASS --------
 * Seat number: 7
 * Section:     Economy
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 2
 * -------- BOARDING PASS --------
 * Seat number: 8
 * Section:     Economy
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 2
 * -------- BOARDING PASS --------
 * Seat number: 9
 * Section:     Economy
 * -------------------------------
 *
 * Please type 1 for First Class
 * Please type 2 for Economy
 * Type 0 to stop: 2
 * -------- BOARDING PASS --------
 * Seat number: 10
 * Section:     Economy
 * -------------------------------
 *
 * Sorry, the plane is completely booked. Next flight leaves in 3 hours.
 */
