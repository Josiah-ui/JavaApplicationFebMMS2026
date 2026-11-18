public class SingleDimensionalArray{
	public static void main(String[] args){
		int age = 25;
		int [] ages = {18,17,16,20,21};
		
		System.out.println("Element of the arrays");
		
		for(int i = 0; i < 5; i++){
			System.out.println(ages[i]);
		}
		System.out.println("-------------------");
		
		if(ages[0] > ages[4]){
			System.out.println("Hiiiiiiiiiiiiii");
		}
		else{
			System.out.println("ohhhhh");
		}
	}
}