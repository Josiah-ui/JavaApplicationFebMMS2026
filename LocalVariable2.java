public class LocalVariable2{
	public static void main(String[] agrs){
		checkNumber();
	}
	
	public static void checkNumber(){
		
		int x = 30;
		
		if(x == 30){
			System.out.printf("Hello %d, your are equal to 30",x);
		}
		else{
			System.out.printf("Sorry %d, you are not equal to 30",x);
		}
	}
}