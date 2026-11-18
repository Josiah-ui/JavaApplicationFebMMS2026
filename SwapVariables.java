public class SwapVariables{
	public static void main(String[] args){
		
		
		int x = 10;
		int y = 20;
		
		System.out.println("Before swap: x = " + x + ", y = " + y);
		
		int temp = x;
        x = y;
        y = temp;
 
        System.out.println("After swap: x = " + x + ", y = " + y);
	}
}