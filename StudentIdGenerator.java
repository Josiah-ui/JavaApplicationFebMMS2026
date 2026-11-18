import java.util.Random;

public class StudentIdGenerator{
    public static void main(String[] args) {
        Random random = new Random();

        int number = random.nextInt(90000) + 10000;
        String id = "STU" + number;

        System.out.println("Student ID: " + id);
    }
}