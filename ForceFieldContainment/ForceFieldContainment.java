import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Oh no! You and your secret spy group sent into known
 * alien controlled territory have been discovered. The aliens
 * have somehow suspect that they have spies somewhere in their 
 * territory or near their territory. You know your coordinates,
 * and somehow, command head quarters was able to send you the
 * coordinates of the force field the aliens put up to trap you guys.
 * You now need to figure out if you and your group are trapped in
 * this force field or not.
 * 
 * Your survival now depends on you being able to tell if you are
 * contained within this force field. Therefore, write a Java method
 * that takes in 2 inputs and tells whether or not you are trapped in
 * the force field. You will be provided with an inner class called Point
 * which holds the x and y position of a point. The first input will be
 * an object type Point that will contain your location. The second input
 * will be a Point array that will contain the locations of the beams that
 * were used to set up the force field. These locations, given in clockwise
 * order, form a polygon that makes up the force field. Your method should
 * return a boolean value that indicates whether or not you're trapped.
 * You can write any helper functions you feel are necessary.
 * 
 * Sample Input:
 * 
 * 1. Point (0, 0), { Point (-1, -1), Point (-1, 1), Point (1, 1), Point (1, -1) }
 * 2. Point (5, 0), { Point (-1, -1), Point (-1, 1), Point (1, 1), Point (1, -1) }
 * 3. Point (0.5, 0.5), { Point (0, 0), Point (1, 1), Point (1, 0) }
 * 4. Point (0, 0), { Point (0, 0), Point (1, 1), Point (1, 0) }
 * 4. Point (-1, 0), { Point (0, 0), Point (1, 1), Point (1, 0) }
 * 
 * 
 * Sample Output:
 * 
 * 1. True
 * 2. False
 * 3. True
 * 4. True
 * 5. False
 *
 */
public class ForceFieldContainment {
	
	
	
	
	/*	Write the body of this method
	*	ourLocation is the location of the spies
	*	pointArray is the verticies of the polygon made by the forcefields
	*	the verticies are in clockwise order around the shape
	*/
	public static boolean forceFieldContains(Point ourLocation, Point[] pointArray) {
		for (int i = 0; i < pointArray.length; i++) {
			for (int j = i + 1; j < pointArray.length; j++) {
				for (int k = j + 1; k < pointArray.length; k++) {
					Vector v1 = pointArray[i].getVector();
					Vector v2 = pointArray[j].getVector();
					Vector v3 = pointArray[k].getVector();
					if (Vector.triangleContains(v1, v2, v3, ourLocation.getVector())) return true;
				}
			}
		}
		return false;
	}

	public class Point {
		public final double x;
		public final double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public Vector getVector() {
			return new Vector(x, y);
		}
	}
	
	public static void main (String[] args) throws IOException {
		ForceFieldContainment x = new ForceFieldContainment();
		BufferedReader br = new BufferedReader (new FileReader ("ForceFieldContainment.txt"));
		while (br.ready()) {
			String line = br.readLine();
			line = line.trim();
			String[] input = line.split(",");
			double [] inputNumbers = new double [input.length];
			for (int i = 0; i < input.length; i++) {
				inputNumbers[i] = Double.parseDouble(input[i]);
			}
			Point ourLocation = x.new Point (inputNumbers[0], inputNumbers[1]);
			int numberOfVerticesInPolygon = (inputNumbers.length - 2) / 2;
			Point[] polygon = new Point[numberOfVerticesInPolygon];
			int j = 0;
			for (int i = 2; i < inputNumbers.length; i+=2) {
				polygon[j] = x.new Point (inputNumbers[i], inputNumbers[i+1]);
				j++;
			}
			System.out.println (forceFieldContains (ourLocation, polygon));
		}
		br.close();
	}

}
