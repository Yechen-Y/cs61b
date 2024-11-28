public class ex4{
	public static void windowPosSum(int[] a, int n){
		for(int i = 0; i < a.length; i += 1){
			if(a[i] < 0){
				continue;
			}
			for(int j = 1; j < n + 1; j += 1){
				if(i + j > a.length - 1){
					break;
				}
				a[i] = a[i] + a[i + j];
			}
		}
	}

	public static void main(String[] args){
		int[] a = {1, 2, -3, 4, 5, 4};
    	int n = 3;
    	windowPosSum(a, n);
		
		System.out.println(java.util.Arrays.toString(a));	
	}
}
