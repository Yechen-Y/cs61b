public class array{
	public static int max(int[] m){
		int index = 0;
		int max_num = 0;
		while(index < m.length){
			if(m[index] > max_num){
				max_num = m[index];
			}
			index += 1;	
		}
		return max_num;
	}
	public static void main(String[] args){
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.println(max(numbers)); 
	}
}
