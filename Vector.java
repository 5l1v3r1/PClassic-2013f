/**
 * This class was written for PlanetaryProjectiles
 * and then expanded for ForceFieldContainment.
 */
public class Vector {
	private double x;
	private double y;
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double dot(Vector v1) {
		return this.x * v1.x + this.y * v1.y;
	}
	public Vector scale(double s) {
		return new Vector(this.x * s, this.y * s);
	}
	public Vector add(Vector v1) {
		return new Vector(this.x + v1.x, this.y + v1.y);
	}
	public String toString() {
		return "<" + this.x + "," + this.y + ">";
	}
	
    /**
     * Solves a 2x2 linear system using determinants
     */
	public static Vector combination(Vector v1, Vector v2, Vector v3) {
		double det = 1/(v1.x*v2.y - v1.y*v2.x);
		double x = det * v2.y * v3.x - det * v2.x * v3.y;
		double y = -det * v1.y * v3.x + det * v1.x * v3.y;
		return new Vector(x, y);
	}
	
    /**
     * Checks if a triangle contains a point.
     */
	public static boolean triangleContains(Vector v1, Vector v2, Vector v3, Vector point) {
		Vector v1tov2 = v2.add(v1.scale(-1));
		Vector v1tov3 = v3.add(v1.scale(-1));
		Vector v1ToPoint = point.add(v1.scale(-1));
		Vector comb = Vector.combination(v1tov2, v1tov3, v1ToPoint);
		if (comb.x > 1 || comb.x < 0 || comb.y > 1 || comb.y < 0) return false;
		// try again
		Vector v2tov1 = v1.add(v2.scale(-1));
		Vector v2tov3 = v3.add(v2.scale(-1));
		Vector v2ToPoint = point.add(v2.scale(-1));
		comb = Vector.combination(v2tov1, v2tov3, v2ToPoint);
		if (comb.x > 1 || comb.x < 0 || comb.y > 1 || comb.y < 0) return false;
		return true;
	}
	
}