// Exercise 6.11 - one-dimensional array operations
public class Exercise6_11 {
    public static void main(String[] args) {
        int[] counts = new int[10];
        int[] bonus = new int[15];
        int[] bestScores = {95, 88, 92, 79, 100};

        // some non-zero starting data so the effect of each statement is visible
        for (int i = 0; i < counts.length; i++)
            counts[i] = i * 3 + 1;

        // a) set the 10 elements of integer array counts to zero
        for (int i = 0; i < counts.length; i++)
            counts[i] = 0;
        System.out.println("a) counts after being zeroed:");
        for (int value : counts)
            System.out.print(value + " ");
        System.out.println();

        // b) add one to each of the 15 elements of integer array bonus
        for (int i = 0; i < bonus.length; i++)
            bonus[i]++;
        System.out.println("\nb) bonus after adding one to every element:");
        for (int value : bonus)
            System.out.print(value + " ");
        System.out.println();

        // c) display the five values of integer array bestScores in column format
        System.out.println("\nc) bestScores in column format:");
        for (int i = 0; i < bestScores.length; i++)
            System.out.printf("%d%n", bestScores[i]);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * a) counts after being zeroed:
 * 0 0 0 0 0 0 0 0 0 0 
 *
 * b) bonus after adding one to every element:
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
 *
 * c) bestScores in column format:
 * 95
 * 88
 * 92
 * 79
 * 100
 */
