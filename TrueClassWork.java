import java.util.Random;

public class TrueClassWork{
	public static void main(String[] args){
		
		
		Random rand = new Random();
		
		boolean answer = rand.nextBoolean();
		
		
		System.out.printf("Do you love java ? %b%n",answer);
	}
}