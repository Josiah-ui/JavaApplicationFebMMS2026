public class ClassWorkAvArgs{
	public static void main(String[] args){
		EvenCountSum(2,11,13,8,6,4,9,17,3,10);
		OddCountSum(3,16,9,5,8);
	}
	public static void  EvenCountSum(int... numbers){
		int EvenCount = 0;
		int OddSum = 0;
		
		for (int : numbers){
			EvenCount ++;
		}
		else{
			OddSum += num;
		}
		System.out.println("Even count:" + EvenCount );
		System.out.println("OddSum: " + OddSum);
	}
}