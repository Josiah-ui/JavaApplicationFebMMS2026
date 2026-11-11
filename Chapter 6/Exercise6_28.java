// Exercise 6.28 (Simulation: The Tortoise and the Hare)
// Rules (Fig. 6.25):
//   Tortoise: fast plod   50%  +3 squares   (i = 1-5)
//             slip        20%  -6 squares   (i = 6-7)
//             slow plod   30%  +1 square    (i = 8-10)
//   Hare:     sleep       20%   0           (i = 1-2)
//             big hop     20%  +9           (i = 3-4)
//             big slip    10%  -12          (i = 5)
//             small hop   30%  +1           (i = 6-8)
//             small slip  20%  -2           (i = 9-10)
import java.security.SecureRandom;

public class Exercise6_28 {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int FINISH = 70;

    public static void main(String[] args) {
        int tortoise = 1;
        int hare = 1;

        System.out.println("BANG !!!!!");
        System.out.println("AND THEY'RE OFF !!!!!");

        while (tortoise < FINISH && hare < FINISH) {
            tortoise = moveTortoise(tortoise);
            hare = moveHare(hare);
            printRace(tortoise, hare);
        }

        if (tortoise >= FINISH && hare >= FINISH)
            System.out.println("It's a tie.");
        else if (tortoise >= FINISH)
            System.out.println("TORTOISE WINS!!! YAY!!!");
        else
            System.out.println("Hare wins. Yuch.");
    }

    public static int moveTortoise(int position) {
        int i = 1 + randomNumbers.nextInt(10);
        if (i <= 5)
            position += 3;          // fast plod
        else if (i <= 7)
            position -= 6;          // slip
        else
            position += 1;          // slow plod
        return Math.max(position, 1);
    }

    public static int moveHare(int position) {
        int i = 1 + randomNumbers.nextInt(10);
        if (i <= 2)
            position += 0;          // sleep
        else if (i <= 4)
            position += 9;          // big hop
        else if (i == 5)
            position -= 12;         // big slip
        else if (i <= 8)
            position += 1;          // small hop
        else
            position -= 2;          // small slip
        return Math.max(position, 1);
    }

    // one line of 70 positions: T for the tortoise, H for the hare, OUCH!!! if they share a square
    public static void printRace(int tortoise, int hare) {
        StringBuilder line = new StringBuilder(" ".repeat(FINISH));
        int t = Math.min(tortoise, FINISH);
        int h = Math.min(hare, FINISH);

        if (t == h) {
            line.replace(t - 1, t - 1 + "OUCH!!!".length(), "OUCH!!!");
        } else {
            line.setCharAt(t - 1, 'T');
            line.setCharAt(h - 1, 'H');
        }
        System.out.println(line.toString().stripTrailing());
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * BANG !!!!!
 * AND THEY'RE OFF !!!!!
 * OUCH!!!
 *  H T
 * H     T
 *  H     T
 * H         T
 *  H           T
 *  H              T
 *   H                T
 *   H                   T
 *   H                    T
 * H                T
 *  H                  T
 *  H                   T
 * H              T
 * H        T
 *  H          T
 *   H            T
 *    H     T
 *             OUCH!!!
 *                T     H
 *                   T  H
 *                    T  H
 *                       T        H
 *                          T              H
 *                             T         H
 *                                T      H
 *                                   T    H
 *                             T           H
 * ... (73 more lines omitted) ...
 *          H                                                T
 *           H                                                T
 *            H                                                T
 * H                                                              T
 * H                                                               T
 *  H                                                                 T
 *  H                                                           T
 *   H                                                             T
 * H                                                         T
 *  H                                                           T
 *           H                                                     T
 * H                                                                  T
 * H                                                                    T
 * TORTOISE WINS!!! YAY!!!
 */
