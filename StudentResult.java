public class StudentResult {

    public static void calculateResult(int score) {
        System.out.println("Single Score: " + score);
    }

    public static void calculateResult(int score1, int score2) {
        double average = (score1 + score2) / 2.0;
        System.out.println("Average of Two Scores: " + average);
    }

    public static void calculateResult(int score1, int score2, int score3) {
        double average = (score1 + score2 + score3) / 3.0;
        System.out.println("Average of Three Scores: " + average);
    }

    public static void calculateResult(double score1, double score2) {
        double average = (score1 + score2) / 2;
        System.out.println("Average of Two Decimal Scores: " + average);
    }

    public static void main(String[] args) {
        calculateResult(80);
        calculateResult(70, 80);
        calculateResult(70, 80, 90);
        calculateResult(82.5, 88.0);
    }
	
}