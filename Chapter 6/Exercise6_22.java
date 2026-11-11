// Exercise 6.22 (Knight's Tour: Brute-Force Approaches) - parts (a), (b) and (c)
// Part (d) is a discussion question and is answered in the Word document.
import java.util.Random;

public class Exercise6_22 {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical   = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final Random random = new Random();

    // Part (c) stops after this many seconds if no full tour has been found.
    // Set to 0 to run until a full tour is found (this can take hours).
    private static final int TIME_LIMIT_SECONDS = 60;

    public static void main(String[] args) {
        int[][] board = new int[8][8];

        // ---- part (a): one random tour ----
        int length = randomTour(board);
        System.out.println("Part (a): one random tour");
        for (int[] row : board) {
            for (int square : row)
                System.out.printf("%4d", square);
            System.out.println();
        }
        System.out.printf("The knight made %d moves (visited %d squares).%n%n", length - 1, length);

        // ---- part (b): 1,000 tours ----
        int[] tally = new int[65];   // tally[n] = number of tours that visited n squares
        for (int tour = 1; tour <= 1000; tour++)
            tally[randomTour(board)]++;
        System.out.println("Part (b): 1,000 random tours");
        printTally(tally);
        System.out.printf("Best result: %d squares visited%n%n", longest(tally));

        // ---- part (c): keep going until a full tour appears (or the time limit is reached) ----
        tally = new int[65];
        long attempts = 0;
        long start = System.nanoTime();
        boolean found = false;
        while (!found) {
            int visited = randomTour(board);
            tally[visited]++;
            attempts++;
            if (visited == 64)
                found = true;
            else if (TIME_LIMIT_SECONDS > 0 && (attempts & 0xFFFFF) == 0
                    && (System.nanoTime() - start) / 1_000_000_000L >= TIME_LIMIT_SECONDS)
                break;
        }
        double seconds = (System.nanoTime() - start) / 1_000_000_000.0;

        System.out.println("Part (c): random tours until a full tour is found");
        printTally(tally);
        if (found) {
            System.out.printf("A full tour was found after %,d tours (%.1f seconds).%n", attempts, seconds);
            for (int[] row : board) {
                for (int square : row)
                    System.out.printf("%4d", square);
                System.out.println();
            }
        } else {
            System.out.printf("No full tour after %,d tours in %.1f seconds (time limit reached). Best: %d squares.%n",
                    attempts, seconds, longest(tally));
        }
    }

    // One random tour from a random starting square; each step chooses at random among the legal moves.
    // Returns the number of squares visited (1-64) and leaves the numbered tour in board.
    public static int randomTour(int[][] board) {
        for (int[] row : board)
            java.util.Arrays.fill(row, 0);

        int currentRow = random.nextInt(8);
        int currentColumn = random.nextInt(8);
        int counter = 1;
        board[currentRow][currentColumn] = counter;

        int[] legal = new int[8];
        while (true) {
            int count = 0;
            for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
                int r = currentRow + vertical[moveNumber];
                int c = currentColumn + horizontal[moveNumber];
                if (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == 0)
                    legal[count++] = moveNumber;
            }
            if (count == 0)
                return counter;

            int chosen = legal[random.nextInt(count)];
            currentRow += vertical[chosen];
            currentColumn += horizontal[chosen];
            board[currentRow][currentColumn] = ++counter;
        }
    }

    public static void printTally(int[] tally) {
        System.out.printf("%-18s%s%n", "Squares visited", "Number of tours");
        for (int n = 1; n < tally.length; n++)
            if (tally[n] > 0)
                System.out.printf("%-18d%d%n", n, tally[n]);
    }

    public static int longest(int[] tally) {
        for (int n = tally.length - 1; n >= 1; n--)
            if (tally[n] > 0)
                return n;
        return 0;
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Part (a): one random tour
 *   29   0  35   0  17   0   3   0
 *   34   0  28   0   2   0   0   0
 *    0  30  33  18   0  16   1   4
 *    0  27   0   0  32   5   0   0
 *    0   0  31   6  19   0  15  12
 *   26  23  20   0  10  13   8   0
 *    0   0  25  22   7   0  11  14
 *   24  21   0   0   0   9   0   0
 * The knight made 34 moves (visited 35 squares).
 *
 * Part (b): 1,000 random tours
 * Squares visited   Number of tours
 * 4                 1
 * 5                 1
 * 6                 2
 * 7                 2
 * 8                 3
 * 9                 1
 * 10                3
 * 11                2
 * 12                3
 * 13                8
 * 14                9
 * 15                7
 * 16                9
 * 17                11
 * 18                14
 * 19                8
 * 20                15
 * 21                11
 * 22                12
 * 23                14
 * 24                20
 * 25                18
 * 26                26
 * 27                23
 * 28                25
 * 29                33
 * 30                29
 * 31                26
 * 32                39
 * 33                32
 * 34                39
 * 35                27
 * 36                39
 * 37                29
 * 38                46
 * 39                32
 * 40                50
 * 41                38
 * 42                53
 * 43                35
 * 44                37
 * 45                28
 * 46                24
 * 47                28
 * 48                21
 * 49                20
 * 50                12
 * 51                7
 * 52                8
 * 53                5
 * 54                9
 * 55                3
 * 56                2
 * 57                1
 * Best result: 57 squares visited
 *
 * Part (c): random tours until a full tour is found
 * Squares visited   Number of tours
 * 4                 675
 * 5                 651
 * 6                 1821
 * 7                 1718
 * 8                 3031
 * 9                 2999
 * 10                4451
 * 11                4134
 * 12                6047
 * 13                5961
 * 14                8115
 * 15                7761
 * 16                10415
 * 17                9976
 * 18                12908
 * 19                12643
 * 20                15872
 * 21                15548
 * 22                19309
 * 23                18839
 * 24                23029
 * 25                22061
 * 26                26947
 * 27                25727
 * 28                31000
 * 29                29631
 * 30                34825
 * 31                33306
 * 32                38707
 * 33                36288
 * 34                42657
 * 35                38831
 * 36                44388
 * 37                40277
 * 38                45623
 * 39                40551
 * 40                44869
 * 41                39235
 * 42                41892
 * 43                35911
 * 44                37671
 * 45                30759
 * 46                30715
 * 47                24629
 * 48                23311
 * 49                17325
 * 50                15917
 * 51                11318
 * 52                9557
 * 53                6070
 * 54                4619
 * 55                2714
 * 56                1853
 * 57                913
 * 58                542
 * 59                211
 * 60                96
 * 61                26
 * 62                10
 * 63                1
 * 64                1
 * A full tour was found after 1,096,887 tours (2.8 seconds).
 *   15  20  41  58  11   2  43  60
 *   40  57  14  19  42  59  10   3
 *   21  16  39  12   1   8  61  44
 *   56  13  18  33  38  45   4   9
 *   17  22  55  46   7  32  37  62
 *   54  49  52  23  34   5  28  31
 *   51  24  47   6  29  26  63  36
 *   48  53  50  25  64  35  30  27
 */
