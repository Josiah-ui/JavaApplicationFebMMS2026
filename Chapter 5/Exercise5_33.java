// Exercise 5.33 (Craps Game Modification) - craps with wagering and "chatter"
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise5_33 {
    private static final SecureRandom randomNumbers = new SecureRandom();

    private enum Status { CONTINUE, WON, LOST }

    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int bankBalance = 1000;
        boolean playAgain = true;

        while (playAgain && bankBalance > 0) {
            System.out.printf("%nBank balance: $%d%n", bankBalance);
            int wager = getWager(input, bankBalance);
            chatter(bankBalance, wager);

            if (playCraps())
                bankBalance += wager;
            else
                bankBalance -= wager;
            System.out.printf("Your new bank balance is $%d%n", bankBalance);

            if (bankBalance == 0) {
                System.out.println("Sorry. You busted!");
            } else {
                chatter(bankBalance, wager);
                System.out.print("Play again (y or n)? ");
                playAgain = input.next().toLowerCase().charAt(0) == 'y';
            }
        }
    }

    // keeps asking until the wager is positive and no more than the bank balance
    public static int getWager(Scanner input, int bankBalance) {
        System.out.print("Enter your wager: ");
        int wager = input.nextInt();
        while (wager <= 0 || wager > bankBalance) {
            System.out.printf("Invalid wager. It must be between 1 and %d. Enter your wager again: ", bankBalance);
            wager = input.nextInt();
        }
        return wager;
    }

    // plays one game of craps; returns true if the player wins
    public static boolean playCraps() {
        int myPoint = 0;
        Status gameStatus;
        int sumOfDice = rollDice();

        switch (sumOfDice) {
            case SEVEN:
            case YO_LEVEN:
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus = Status.LOST;
                break;
            default:
                gameStatus = Status.CONTINUE;
                myPoint = sumOfDice;
                System.out.printf("Point is %d%n", myPoint);
                break;
        }

        while (gameStatus == Status.CONTINUE) {
            sumOfDice = rollDice();
            if (sumOfDice == myPoint)
                gameStatus = Status.WON;
            else if (sumOfDice == SEVEN)
                gameStatus = Status.LOST;
        }

        if (gameStatus == Status.WON)
            System.out.println("Player wins");
        else
            System.out.println("Player loses");

        return gameStatus == Status.WON;
    }

    public static int rollDice() {
        int die1 = 1 + randomNumbers.nextInt(6);
        int die2 = 1 + randomNumbers.nextInt(6);
        int sum = die1 + die2;
        System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);
        return sum;
    }

    // displays a randomly chosen message that fits the current situation
    public static void chatter(int bankBalance, int wager) {
        String[] allIn = {
            "Oh, you're going for broke, huh?",
            "All in! I admire the nerve."
        };
        String[] smallWager = {
            "Aw c'mon, take a chance!",
            "That's a timid bet. Live a little!"
        };
        String[] upBig = {
            "You're up big. Now's the time to cash in your chips!",
            "You're on a roll, but quitting while ahead is smart too."
        };
        String[] general = {
            "Good luck!",
            "Let's see what the dice have to say.",
            "The dice are warming up..."
        };

        String[] pool;
        if (wager == bankBalance)
            pool = allIn;
        else if (bankBalance > 1500)
            pool = upBig;
        else if (wager <= bankBalance / 10)
            pool = smallWager;
        else
            pool = general;

        System.out.println(pool[randomNumbers.nextInt(pool.length)]);
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 *
 * Bank balance: $1000
 * Enter your wager: 5000
 * Invalid wager. It must be between 1 and 1000. Enter your wager again: 200
 * Let's see what the dice have to say.
 * Player rolled 3 + 5 = 8
 * Point is 8
 * Player rolled 2 + 2 = 4
 * Player rolled 1 + 1 = 2
 * Player rolled 6 + 3 = 9
 * Player rolled 6 + 3 = 9
 * Player rolled 6 + 3 = 9
 * Player rolled 4 + 1 = 5
 * Player rolled 4 + 1 = 5
 * Player rolled 2 + 2 = 4
 * Player rolled 1 + 4 = 5
 * Player rolled 2 + 4 = 6
 * Player rolled 3 + 4 = 7
 * Player loses
 * Your new bank balance is $800
 * The dice are warming up...
 * Play again (y or n)? y
 *
 * Bank balance: $800
 * Enter your wager: 200
 * The dice are warming up...
 * Player rolled 1 + 4 = 5
 * Point is 5
 * Player rolled 6 + 2 = 8
 * Player rolled 1 + 5 = 6
 * ... (4 more lines omitted) ...
 * Player rolled 3 + 2 = 5
 * Player wins
 * Your new bank balance is $1000
 * The dice are warming up...
 * Play again (y or n)? y
 *
 * Bank balance: $1000
 * Enter your wager: 200
 * The dice are warming up...
 * Player rolled 1 + 6 = 7
 * Player wins
 * Your new bank balance is $1200
 * Let's see what the dice have to say.
 * Play again (y or n)? n
 */
