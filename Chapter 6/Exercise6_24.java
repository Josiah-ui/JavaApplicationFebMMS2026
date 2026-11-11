// Exercise 6.24 (Eight Queens) - "elimination number" heuristic
// Each square gets a number telling how many squares (including itself) a queen there would eliminate.
// Heuristic: always place the next queen on the available square with the smallest elimination number.
import java.util.Random;

public class Exercise6_24 {
    private static final int N = 8;
    private static final Random random = new Random();

    public static void main(String[] args) {
        // elimination numbers of an empty board (corners are 22)
        boolean[][] empty = new boolean[N][N];
        for (boolean[] row : empty)
            java.util.Arrays.fill(row, true);

        System.out.println("Elimination numbers on an empty board:");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++)
                System.out.printf("%4d", elimination(empty, r, c));
            System.out.println();
        }

        // run the heuristic (ties go to the first square in row-major order)
        int[][] board = new int[N][N];
        int placed = solve(board, false);
        System.out.println("\nHeuristic result (ties broken by taking the first square):");
        printBoard(board);
        System.out.printf("Queens placed: %d (%s)%n", placed, placed == N ? "solved" : "not solved");

        // run it many times with random tie-breaking
        int solved = 0;
        int runs = 1000;
        int[][] firstSolution = null;
        for (int i = 0; i < runs; i++) {
            int[][] b = new int[N][N];
            if (solve(b, true) == N) {
                solved++;
                if (firstSolution == null)
                    firstSolution = b;
            }
        }
        System.out.printf("%nWith random tie-breaking, %d of %d runs placed all eight queens.%n", solved, runs);
        if (firstSolution != null) {
            System.out.println("A solution found that way:");
            printBoard(firstSolution);
        }
    }

    // number of still-available squares (including (r, c) itself) that a queen on (r, c) would eliminate
    public static int elimination(boolean[][] available, int r, int c) {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (available[i][j] && (i == r || j == c || i - j == r - c || i + j == r + c))
                    count++;
        return count;
    }

    // places queens (1 on the board) using the heuristic; returns the number of queens placed
    public static int solve(int[][] board, boolean randomTies) {
        boolean[][] available = new boolean[N][N];
        for (boolean[] row : available)
            java.util.Arrays.fill(row, true);

        int placed = 0;
        while (true) {
            int best = Integer.MAX_VALUE;
            int bestRow = -1, bestColumn = -1, ties = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!available[r][c])
                        continue;
                    int value = elimination(available, r, c);
                    if (value < best) {
                        best = value;
                        bestRow = r;
                        bestColumn = c;
                        ties = 1;
                    } else if (value == best && randomTies) {
                        ties++;
                        if (random.nextInt(ties) == 0) {   // reservoir sampling among the tied squares
                            bestRow = r;
                            bestColumn = c;
                        }
                    }
                }
            }

            if (bestRow == -1)
                return placed;   // no square left

            board[bestRow][bestColumn] = 1;
            placed++;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (i == bestRow || j == bestColumn || i - j == bestRow - bestColumn || i + j == bestRow + bestColumn)
                        available[i][j] = false;
        }
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
 * Elimination numbers on an empty board:
 *   22  22  22  22  22  22  22  22
 *   22  24  24  24  24  24  24  22
 *   22  24  26  26  26  26  24  22
 *   22  24  26  28  28  26  24  22
 *   22  24  26  28  28  26  24  22
 *   22  24  26  26  26  26  24  22
 *   22  24  24  24  24  24  24  22
 *   22  22  22  22  22  22  22  22
 *
 * Heuristic result (ties broken by taking the first square):
 *  Q . . . . . . .
 *  . . . Q . . . .
 *  . . . . . Q . .
 *  . . . . . . . Q
 *  . Q . . . . . .
 *  . . . . Q . . .
 *  . . Q . . . . .
 *  . . . . . . . .
 * Queens placed: 7 (not solved)
 *
 * With random tie-breaking, 208 of 1000 runs placed all eight queens.
 * A solution found that way:
 *  . . . . Q . . .
 *  . Q . . . . . .
 *  . . . . . Q . .
 *  Q . . . . . . .
 *  . . . . . . Q .
 *  . . . Q . . . .
 *  . . . . . . . Q
 *  . . Q . . . . .
 */
