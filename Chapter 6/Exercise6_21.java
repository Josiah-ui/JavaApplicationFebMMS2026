// Exercise 6.21 (Knight's Tour) - parts (b), (c) and (d)
public class Exercise6_21 {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical   = {-1, -2, -2, -1, 1, 2, 2, 1};

    // accessibility numbers of an empty board (from the exercise)
    private static final int[][] BASE_ACCESSIBILITY = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };

    public static void main(String[] args) {
        // ---- part (b): simple tour, always taking the first legal move (type 0..7) ----
        int[][] board = new int[8][8];
        int moves = simpleTour(board, 0, 0);
        System.out.println("Part (b): simple tour starting in the corner (0, 0), trying move types 0-7 in order");
        printBoard(board);
        System.out.printf("The knight visited %d squares, so it made %d moves.%n%n", moves, moves - 1);

        // ---- part (c): accessibility heuristic ----
        board = new int[8][8];
        moves = heuristicTour(board, 0, 0, false);
        System.out.println("Part (c): accessibility heuristic, tour starting in the corner (0, 0)");
        printBoard(board);
        System.out.printf("Squares visited: %d (%s)%n%n", moves, moves == 64 ? "full tour" : "not a full tour");

        int fullTours = 0;
        System.out.println("Part (c): 64 tours, one starting from each square (number of squares visited)");
        int[][] results = new int[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                results[r][c] = heuristicTour(new int[8][8], r, c, false);
                if (results[r][c] == 64)
                    fullTours++;
            }
        }
        printResults(results);
        System.out.printf("Full tours with the accessibility heuristic: %d of 64%n%n", fullTours);

        // ---- part (d): ties broken by looking ahead ----
        board = new int[8][8];
        moves = heuristicTour(board, 0, 0, true);
        System.out.println("Part (d): accessibility heuristic with look-ahead tie-breaking, starting in (0, 0)");
        printBoard(board);
        System.out.printf("Squares visited: %d (%s)%n%n", moves, moves == 64 ? "full tour" : "not a full tour");

        fullTours = 0;
        System.out.println("Part (d): 64 tours, one starting from each square (number of squares visited)");
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                results[r][c] = heuristicTour(new int[8][8], r, c, true);
                if (results[r][c] == 64)
                    fullTours++;
            }
        }
        printResults(results);
        System.out.printf("Full tours with look-ahead tie-breaking: %d of 64%n", fullTours);
    }

    // true if (r, c) is on the board and has not been visited
    public static boolean isOpen(int[][] board, int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == 0;
    }

    // part (b): returns the number of squares visited
    public static int simpleTour(int[][] board, int startRow, int startColumn) {
        int currentRow = startRow;
        int currentColumn = startColumn;
        int counter = 1;
        board[currentRow][currentColumn] = counter;

        boolean moved = true;
        while (moved) {
            moved = false;
            for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
                int newRow = currentRow + vertical[moveNumber];
                int newColumn = currentColumn + horizontal[moveNumber];
                if (isOpen(board, newRow, newColumn)) {
                    currentRow = newRow;
                    currentColumn = newColumn;
                    board[currentRow][currentColumn] = ++counter;
                    moved = true;
                    break;
                }
            }
        }
        return counter;
    }

    // parts (c) and (d): returns the number of squares visited
    public static int heuristicTour(int[][] board, int startRow, int startColumn, boolean lookAhead) {
        int[][] accessibility = new int[8][8];
        for (int r = 0; r < 8; r++)
            accessibility[r] = BASE_ACCESSIBILITY[r].clone();

        int currentRow = startRow;
        int currentColumn = startColumn;
        int counter = 1;
        board[currentRow][currentColumn] = counter;
        occupy(accessibility, currentRow, currentColumn);

        while (counter < 64) {
            int bestMove = -1;
            int bestAccessibility = Integer.MAX_VALUE;
            int bestLookAhead = Integer.MAX_VALUE;

            for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
                int newRow = currentRow + vertical[moveNumber];
                int newColumn = currentColumn + horizontal[moveNumber];
                if (!isOpen(board, newRow, newColumn))
                    continue;

                int acc = accessibility[newRow][newColumn];
                if (acc < bestAccessibility) {
                    bestMove = moveNumber;
                    bestAccessibility = acc;
                    bestLookAhead = lookAhead ? lookAheadValue(board, accessibility, newRow, newColumn) : 0;
                } else if (acc == bestAccessibility && lookAhead) {
                    // tie: prefer the square whose next move reaches the lowest accessibility number
                    int value = lookAheadValue(board, accessibility, newRow, newColumn);
                    if (value < bestLookAhead) {
                        bestMove = moveNumber;
                        bestLookAhead = value;
                    }
                }
            }

            if (bestMove == -1)
                break;   // the knight is stuck

            currentRow += vertical[bestMove];
            currentColumn += horizontal[bestMove];
            board[currentRow][currentColumn] = ++counter;
            occupy(accessibility, currentRow, currentColumn);
        }
        return counter;
    }

    // lowest accessibility number among the open squares the knight could reach from (r, c)
    public static int lookAheadValue(int[][] board, int[][] accessibility, int r, int c) {
        int lowest = Integer.MAX_VALUE;
        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            int nextRow = r + vertical[moveNumber];
            int nextColumn = c + horizontal[moveNumber];
            if (isOpen(board, nextRow, nextColumn))
                lowest = Math.min(lowest, accessibility[nextRow][nextColumn]);
        }
        return lowest;   // MAX_VALUE if there is no further move
    }

    // after the knight occupies (r, c), every square reachable from it becomes one step less accessible
    public static void occupy(int[][] accessibility, int r, int c) {
        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            int nextRow = r + vertical[moveNumber];
            int nextColumn = c + horizontal[moveNumber];
            if (nextRow >= 0 && nextRow < 8 && nextColumn >= 0 && nextColumn < 8)
                accessibility[nextRow][nextColumn]--;
        }
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int square : row)
                System.out.printf("%4d", square);
            System.out.println();
        }
    }

    public static void printResults(int[][] results) {
        for (int[] row : results) {
            for (int value : row)
                System.out.printf("%4d", value);
            System.out.println();
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Part (b): simple tour starting in the corner (0, 0), trying move types 0-7 in order
 *    1  10  23  42   7   4  13  18
 *   24  41   8   3  12  17   6  15
 *    9   2  11  22   5  14  19  32
 *    0  25  40  35  20  31  16   0
 *    0  36  21   0  39   0  33  30
 *   26   0  38   0  34  29   0   0
 *   37   0   0  28   0   0   0   0
 *    0  27   0   0   0   0   0   0
 * The knight visited 42 squares, so it made 41 moves.
 *
 * Part (c): accessibility heuristic, tour starting in the corner (0, 0)
 *    1  22   3  18  25  30  13  16
 *    4  19  24  29  14  17  34  31
 *   23   2  21  26  35  32  15  12
 *   20   5  56  49  28  41  36  33
 *   57  50  27  42  61  54  11  40
 *    6  43  60  55  48  39  64  37
 *   51  58  45   8  53  62  47  10
 *   44   7  52  59  46   9  38  63
 * Squares visited: 64 (full tour)
 *
 * Part (c): 64 tours, one starting from each square (number of squares visited)
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  60  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 * Full tours with the accessibility heuristic: 63 of 64
 *
 * Part (d): accessibility heuristic with look-ahead tie-breaking, starting in (0, 0)
 *    1  34   3  18  51  32  13  16
 *    4  19  60  33  14  17  54  31
 *   41   2  35  50  61  52  15  12
 *   20   5  40  59  36  55  30  53
 *   39  42  37  62  49  64  11  26
 *    6  21  58  45  56  27  48  29
 *   43  38  23   8  63  46  25  10
 *   22   7  44  57  24   9  28  47
 * Squares visited: 64 (full tour)
 *
 * Part (d): 64 tours, one starting from each square (number of squares visited)
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 *   64  64  64  64  64  64  64  64
 * Full tours with look-ahead tie-breaking: 64 of 64
 */
