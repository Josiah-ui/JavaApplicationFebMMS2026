public class AmericanFlag{
	public static void main(String[] args){
		for (int row = 1; row <= 9; row++) {
            if (row % 2 == 1) {
                System.out.print("* * * * * * ");
            } else {
                System.out.print(" * * * * *  ");
            }
            System.out.println("==================================");
        }
		for (int row = 1; row <= 6; row++) {
            System.out.println("==============================================");
        }
	}
}