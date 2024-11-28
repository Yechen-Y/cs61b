public class class_Triangle{
	public static void drawTriangle(int N){
		int row = 0;
		String result = "*";
		while(row < N){
			System.out.println(result);
			result += "*";
			row += 1;
		}
	}
	public static void main(String[] args){
		drawTriangle(10);	
	}
}
