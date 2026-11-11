// Exercise 6.26 (Knight's Tour: Closed-Tour Test)
// The accessibility-heuristic tour of Exercise 6.22(c)/(d), extended to test whether a full tour is also a closed tour:
// the 64th square must be one knight's move away from the square where the tour started.
public class Exercise6_26 {
    private static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] vertical   = {-1, -2, -2, -1, 1, 2, 2, 1};

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

    private static int lastRow, lastColumn;   // position of the knight when a tour ends

    public static void main(String[] args) {
        for (int pass = 0; pass < 2; pass++) {
            boolean lookAhead = (pass == 1);
            System.out.println(lookAhead
                    ? "Accessibility heuristic with look-ahead tie-breaking:"
                    : "Accessibility heuristic (ties broken by taking the first square):");

            int fullTours = 0;
            int closedTours = 0;
            int[][] firstClosed = null;
            int firstClosedRow = -1, firstClosedColumn = -1;

            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    int[][] board = new int[8][8];
                    int visited = tour(board, r, c, lookAhead);

                    if (visited == 64) {
                        fullTours++;
                        if (isClosed(r, c)) {
                            closedTours++;
                            if (firstClosed == null) {
                                firstClosed = board;
                                firstClosedRow = r;
                                firstClosedColumn = c;
                            }
                        }
                    }
                }
            }

            System.out.printf("  Full tours: %d of 64, closed tours: %d%n", fullTours, closedTours);
            if (firstClosed != null) {
                System.out.printf("  First closed tour found (started at row %d, column %d):%n", firstClosedRow, firstClosedColumn);
                for (int[] row : firstClosed) {
                    System.out.print("  ");
                    for (int square : row)
                        System.out.printf("%4d", square);
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    // closed tour test: is the last square one knight's move from the starting square?
    public static boolean isClosed(int startRow, int startColumn) {
        for (int moveNumber = 0; moveNumber < 8; moveNumber++)
            if (lastRow + vertical[moveNumber] == startRow && lastColumn + horizontal[moveNumber] == startColumn)
                return true;
        return false;
    }

    public static boolean isOpen(int[][] board, int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == 0;
    }

    // returns the number of squares visited; records the final position in lastRow / lastColumn
    public static int tour(int[][] board, int startRow, int startColumn, boolean lookAhead) {
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
                    int value = lookAheadValue(board, accessibility, newRow, newColumn);
                    if (value < bestLookAhead) {
                        bestMove = moveNumber;
                        bestLookAhead = value;
                    }
                }
            }

            if (bestMove == -1)
                break;

            currentRow += vertical[bestMove];
            currentColumn += horizontal[bestMove];
            board[currentRow][currentColumn] = ++counter;
            occupy(accessibility, currentRow, currentColumn);
        }

        lastRow = currentRow;
        lastColumn = currentColumn;
        return counter;
    }

    public static int lookAheadValue(int[][] board, int[][] accessibility, int r, int c) {
        int lowest = Integer.MAX_VALUE;
        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            int nextRow = r + vertical[moveNumber];
            int nextColumn = c + horizontal[moveNumber];
            if (isOpen(board, nextRow, nextColumn))
                lowest = Math.min(lowest, accessibility[nextRow][nextColumn]);
        }
        return lowest;
    }

    public static void occupy(int[][] accessibility, int r, int c) {
        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            int nextRow = r + vertical[moveNumber];
            int nextColumn = c + horizontal[moveNumber];
            if (nextRow >= 0 && nextRow < 8 && nextColumn >= 0 && nextColumn < 8)
                accessibility[nextRow][nextColumn]--;
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Accessibility heuristic (ties broken by taking the first square):
 *   Full tours: 63 of 64, closed tours: 4
 *   First closed tour found (started at row 4, column 1):
 *     14  11  16  47  30   9  32  41
 *     17  48  13  10  43  40  29   8
 *     12  15  52  39  46  31  42  33
 *     53  18  49  44  51  56   7  28
 *     22   1  54  61  38  45  34  57
 *     19  62  21  50  55  60  27   6
 *      2  23  64  37   4  25  58  35
 *     63  20   3  24  59  36   5  26
 *
 * Accessibility heuristic with look-ahead tie-breaking:
 *   Full tours: 64 of 64, closed tours: 13
 *   First closed tour found (started at row 0, column 3):
 *     34  19  16   1  32  49  14  63
 *     17   2  33  50  15  64  31  48
 *     20  35  18  43  54  47  62  13
 *      3  40  51  46  61  42  53  30
 *     36  21  44  41  52  55  12  59
 *      7   4  39  56  45  60  29  26
 *     22  37   6   9  24  27  58  11
 *      5   8  23  38  57  10  25  28
 */
