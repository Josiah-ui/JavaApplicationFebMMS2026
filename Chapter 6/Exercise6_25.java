// Exercise 6.25 (Eight Queens: Brute-Force Approaches) - parts (a) and (b)
// Parts (c) and (d) are discussion questions and are answered in the Word document.
import java.util.Random;

public class Exercise6_25 {
    private static final int N = 8;
    private static final Random random = new Random();

    // data for the exhaustive search
    private static int[] queenRow = new int[N];
    private static int[] queenColumn = new int[N];
    private static long solutions = 0;
    private static long placementsTried = 0;
    private static int[][] firstSolution = null;

    public static void main(String[] args) {
        // ---- part (a): random brute force ----
        // Queens are put on randomly chosen squares that are not attacked; if the board fills up
        // before eight queens are placed, the attempt is thrown away and a new one is started.
        int attempts = 0;
        int[][] board;
        do {
            attempts++;
            board = new int[N][N];
        } while (!randomAttempt(board));

        System.out.println("Part (a): random brute force");
        printBoard(board);
        System.out.printf("A solution was found on attempt number %d%n%n", attempts);

        // average number of attempts over many runs
        long totalAttempts = 0;
        int runs = 1000;
        for (int i = 0; i < runs; i++) {
            int tries = 0;
            do {
                tries++;
            } while (!randomAttempt(new int[N][N]));
            totalAttempts += tries;
        }
        System.out.printf("Average number of attempts over %d runs: %.1f%n%n", runs, (double) totalAttempts / runs);

        // ---- part (b): exhaustive search ----
        long start = System.nanoTime();
        search(0, 0);
        double ms = (System.nanoTime() - start) / 1_000_000.0;

        System.out.println("Part (b): exhaustive search over all combinations of eight squares");
        System.out.printf("Number of ways to choose 8 squares out of 64: %,d%n", combinations(64, 8));
        System.out.printf("Safe partial placements examined: %,d%n", placementsTried);
        System.out.printf("Different solutions found: %d%n", solutions);
        System.out.printf("Time: %.1f ms%n", ms);
        System.out.println("The first solution found:");
        printBoard(firstSolution);
    }

    // one random attempt; returns true if all eight queens were placed
    public static boolean randomAttempt(int[][] board) {
        boolean[][] attacked = new boolean[N][N];
        for (int queens = 0; queens < N; queens++) {
            int free = 0;
            for (int r = 0; r < N; r++)
                for (int c = 0; c < N; c++)
                    if (!attacked[r][c])
                        free++;
            if (free == 0)
                return false;

            int pick = random.nextInt(free);
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (attacked[r][c])
                        continue;
                    if (pick-- == 0) {
                        board[r][c] = 1;
                        for (int i = 0; i < N; i++)
                            for (int j = 0; j < N; j++)
                                if (i == r || j == c || i - j == r - c || i + j == r + c)
                                    attacked[i][j] = true;
                    }
                }
            }
        }
        return true;
    }

    // chooses squares in increasing order (0..63) for queens number 'placed'..7 and backtracks as soon as
    // a queen would attack one already on the board
    public static void search(int firstSquare, int placed) {
        if (placed == N) {
            solutions++;
            if (firstSolution == null) {
                firstSolution = new int[N][N];
                for (int i = 0; i < N; i++)
                    firstSolution[queenRow[i]][queenColumn[i]] = 1;
            }
            return;
        }
        for (int square = firstSquare; square <= 64 - (N - placed); square++) {
            int r = square / N;
            int c = square % N;
            if (isSafe(r, c, placed)) {
                placementsTried++;
                queenRow[placed] = r;
                queenColumn[placed] = c;
                search(square + 1, placed + 1);
            }
        }
    }

    public static boolean isSafe(int r, int c, int placed) {
        for (int i = 0; i < placed; i++)
            if (queenRow[i] == r || queenColumn[i] == c
                    || queenRow[i] - queenColumn[i] == r - c || queenRow[i] + queenColumn[i] == r + c)
                return false;
        return true;
    }

    public static long combinations(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++)
            result = result * (n - k + i) / i;
        return result;
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int square : row)
                System.out.print(square == 1 ? " Q" : " .");
            System.out.println();
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Part (a): random brute force
 *  . . . . Q . . .
 *  Q . . . . . . .
 *  . . . Q . . . .
 *  . . . . . Q . .
 *  . . . . . . . Q
 *  . Q . . . . . .
 *  . . . . . . Q .
 *  . . Q . . . . .
 * A solution was found on attempt number 2
 *
 * Average number of attempts over 1000 runs: 16.0
 *
 * Part (b): exhaustive search over all combinations of eight squares
 * Number of ways to choose 8 squares out of 64: 4,426,165,368
 * Safe partial placements examined: 90,899
 * Different solutions found: 92
 * Time: 30.5 ms
 * The first solution found:
 *  Q . . . . . . .
 *  . . . . Q . . .
 *  . . . . . . . Q
 *  . . . . . Q . .
 *  . . Q . . . . .
 *  . . . . . . Q .
 *  . Q . . . . . .
 *  . . . Q . . . .
 */
