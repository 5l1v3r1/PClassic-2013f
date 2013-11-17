import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpottingShips {

	static int boardWidth = 0;
	static int boardHeight = 0;
	static char[][] input = null;

	/*	Write the body of this method
	 *		N is the width of the grid
	 *		M is the height of the grid
	 *		input is the grid of spots where 'X' is a painted spot
	 *		and '.' is an unpainted spot
	 *		input has size [N][M]
	 */
	public static int numSpots (int N, int M, char[][] _input) {
		input = _input;
		boardWidth = input.length;
		boardHeight = input[0].length;
		
		ArrayList<XPoint> blotch1 = new ArrayList<XPoint>();
		for (int x = 0; x < input.length; x++) {
			for (int y = 0; y < input[x].length; y++) {
				char theChar = input[x][y];
				if (theChar == 'X') {
					XPoint point = new XPoint(x, y);
					blotch1.add(point);
					fanOut(blotch1, point);
					x = input.length;
					break;
				}
			}
		}
		
		// find second blotch
		ArrayList<XPoint> blotch2 = new ArrayList<XPoint>();
		for (int x = 0; x < input.length; x++) {
			for (int y = 0; y < input[x].length; y++) {
				char theChar = input[x][y];
				if (theChar == 'X') {
					XPoint thePoint = new XPoint(x, y);
					if (!blotch1.contains(thePoint)) {
						blotch2.add(thePoint);
						fanOut(blotch2, thePoint);
						x = input.length;
						break;
					}
				}
			}
		}
				
		int maxDist = input.length + input[0].length;
		
		for (XPoint p1 : blotch1) {
			for (XPoint p2 : blotch2) {
				if (p1.distance(p2) < maxDist) {
					maxDist = p1.distance(p2);
				}
			}
		}
		
		return maxDist;
	}

	public static void fanOut(ArrayList<XPoint> points, XPoint cur) {
		for (XPoint point : cur.fanInDimensions(boardWidth, boardHeight)) {
			if (points.contains(point)) continue;
			if (input[point._x][point._y] == 'X') {
				points.add(point);
				fanOut(points, point);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("SpottingShips.txt"));

		while(br.ready()){
			int lines = Integer.parseInt(br.readLine());
			char[][] paint = new char[lines][];
			for (int i = 0; i < lines; i++) {
				String line = br.readLine();
				paint[i] = new char[line.length()];
				for (int j = 0; j < line.length(); j++) {
					paint[i][j] = line.charAt(j);
				}
			}
			System.out.println(numSpots(lines, paint[0].length, paint));
		}
		br.close();
	}


}