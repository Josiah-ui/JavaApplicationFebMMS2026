public class ClassWork3{
    public static void main(String[] args) {
        int[] num = {8, 2, 5, 12, 6, 3, 7, 11, 13, 4};

        int evenCount = 0;
        int oddCount = 0;
        int oddSum = 0;

        // 1 and 2: count even/odd and sum the odd numbers
        for (int i = 0; i < num.length; i++) {
            if (num[i] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
                oddSum = oddSum + num[i];
            }
        }

        System.out.println("Even count = " + evenCount);
        System.out.println("Odd count = " + oddCount);
        System.out.println("Sum of odd numbers = " + oddSum);

        // 3: sum of elements 2 to 6 (indexes 1 to 5)
        int sum2to6 = 0;
        for (int i = 1; i <= 5; i++) {
            sum2to6 = sum2to6 + num[i];
        }

        // sum of elements 3 to 10 (indexes 2 to 9)
        int sum3to10 = 0;
        for (int i = 2; i <= 9; i++) {
            sum3to10 = sum3to10 + num[i];
        }

        int result = sum3to10 - sum2to6;

        System.out.println("Sum of 2-6 elements = " + sum2to6);
        System.out.println("Sum of 3-10 elements = " + sum3to10);
        System.out.println("Result (3-10 minus 2-6) = " + result);
    }
}