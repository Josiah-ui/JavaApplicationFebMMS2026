// Exercise 6.20 (Turtle Graphics)
//
// Turtle commands (Fig. 6.22 plus two extra commands):
//   1        pen up
//   2        pen down
//   3        turn right
//   4        turn left
//   5,10     move forward 10 spaces (replace 10 with any number)
//   6        display the 20-by-20 array
//   9        end of data (sentinel)
//   7        (extra) clear the floor
//   8,r,c    (extra) lift nothing, just move the turtle to row r, column c without drawing
public class Exercise6_20 {
    private static final int SIZE = 20;
    private static final int[] ROW_STEP = {0, 1, 0, -1};   // east, south, west, north
    private static final int[] COL_STEP = {1, 0, -1, 0};

    private static int[][] floor = new int[SIZE][SIZE];
    private static int row, column;
    private static int direction;       // 0 = east, 1 = south, 2 = west, 3 = north
    private static boolean penDown;

    public static void main(String[] args) {
        System.out.println("Program 1: a 12-by-12 square (the example from the exercise)");
        execute(new int[] {2, 5, 12, 3, 5, 12, 3, 5, 12, 3, 5, 12, 1, 6, 9});

        System.out.println("\nProgram 2: the letter H (uses the extra 'move to' command)");
        execute(new int[] {8, 2, 2, 3, 2, 5, 10, 1, 8, 7, 2, 4, 2, 5, 6, 1,
                           8, 2, 8, 3, 2, 5, 10, 1, 6, 9});

        System.out.println("\nProgram 3: a staircase");
        execute(new int[] {2, 5, 3, 3, 5, 3, 4, 5, 3, 3, 5, 3, 4, 5, 3, 3, 5, 3, 4, 5, 3, 3, 5, 3, 1, 6, 9});
    }

    // processes the commands until command 9 (or the end of the array)
    public static void execute(int[] commands) {
        floor = new int[SIZE][SIZE];
        row = 0;              // the turtle starts at position (0, 0)
        column = 0;
        direction = 0;        // facing east
        penDown = false;      // with the pen up

        int i = 0;
        while (i < commands.length && commands[i] != 9) {
            switch (commands[i]) {
                case 1:
                    penDown = false;
                    break;
                case 2:
                    penDown = true;
                    break;
                case 3:
                    direction = (direction + 1) % 4;   // turn right
                    break;
                case 4:
                    direction = (direction + 3) % 4;   // turn left
                    break;
                case 5:
                    i++;
                    move(commands[i]);
                    break;
                case 6:
                    display();
                    break;
                case 7:
                    floor = new int[SIZE][SIZE];
                    break;
                case 8:
                    row = commands[i + 1];
                    column = commands[i + 2];
                    i += 2;
                    break;
                default:
                    System.out.println("Unknown command: " + commands[i]);
            }
            i++;
        }
    }

    // moves the turtle forward; marks the floor with 1s if the pen is down; stops at the edge of the floor
    public static void move(int spaces) {
        if (penDown)
            floor[row][column] = 1;

        for (int step = 1; step <= spaces; step++) {
            int newRow = row + ROW_STEP[direction];
            int newColumn = column + COL_STEP[direction];

            if (newRow < 0 || newRow >= SIZE || newColumn < 0 || newColumn >= SIZE)
                break;   // do not walk off the floor

            row = newRow;
            column = newColumn;
            if (penDown)
                floor[row][column] = 1;
        }
    }

    public static void display() {
        System.out.println("+" + "-".repeat(SIZE) + "+");
        for (int r = 0; r < SIZE; r++) {
            StringBuilder line = new StringBuilder("|");
            for (int c = 0; c < SIZE; c++)
                line.append(floor[r][c] == 1 ? '*' : ' ');
            System.out.println(line.append("|"));
        }
        System.out.println("+" + "-".repeat(SIZE) + "+");
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Program 1: a 12-by-12 square (the example from the exercise)
 * +--------------------+
 * |*************       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*           *       |
 * |*************       |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * +--------------------+
 *
 * Program 2: the letter H (uses the extra 'move to' command)
 * +--------------------+
 * |                    |
 * |                    |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |  *******           |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |  *     *           |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * +--------------------+
 *
 * Program 3: a staircase
 * +--------------------+
 * |****                |
 * |   *                |
 * |   *                |
 * |   ****             |
 * |      *             |
 * |      *             |
 * |      ****          |
 * |         *          |
 * |         *          |
 * |         ****       |
 * |            *       |
 * |            *       |
 * |            *       |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * +--------------------+
 */
