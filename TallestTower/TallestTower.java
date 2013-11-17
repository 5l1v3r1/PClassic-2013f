import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TallestTower {

	public static long gcd(long l1, long l2) {
		if (l1 < 0) return gcd(-l1, l2);
		if (l2 < 0) return gcd(l1, -l2);
		if (l1 < l2) return gcd(l2, l1);
		if (l2 == 0) return l1;
		if (l1 % l2 == 0) return l2;
		return gcd(l2, l1 % l2);
	}
	
	/* 	Write the body of this method
	*	x is the length of one ladder, y is the length of the other
	*/
	public static int getTallestTower(int x, int y) {
		if (x == 1 || y == 1) return 0;
		if (gcd(x, y) != 1) return -1;
		for (int i = x*y - 1; i >= 0; i--) {
			if (!canGenerateTower(x, y, i)) return (int)i;
		}
		return 0;
	}
	
	public static boolean canGenerateTower(int a, int b, int height) {
		for (int m = 0; m <= height/a; m++) {
			int needValue = height - m*a;
			if (m*a > height) continue;
			if (needValue % b == 0) return true;
		}
		return false;
	}
	
	public static void main(String[] args) 
			throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("TallestTower.txt"));
		
		while(reader.ready()){
			String x = reader.readLine();
			String y = reader.readLine();
			
			System.out.println(getTallestTower(Integer.parseInt(x),
					Integer.parseInt(y)));
		}
		
		reader.close();
	}
	
}
