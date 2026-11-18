public class Classwork6 {
    public static void main(String[] args) {
        int[][] numbers = {
            {5, 9, 3, 6, 2},
            {4, 8, 7, 6, 1},
            {8, 9, 3, 1, 0},
            {9, 3, 1, 4, 7}
        };

        int countOfEven = 0;
        int countOfOdd = 0;

        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[row].length; col++) {
                if (numbers[row][col] % 2 == 0) {
                    countOfEven++;
                } else {
                    countOfOdd++;
                }
            }
        }

        
        System.out.printf("The count of even numbers is %d%n", countOfEven);
        System.out.printf("The count of odd numbers is %d%n", countOfOdd);
    }
}