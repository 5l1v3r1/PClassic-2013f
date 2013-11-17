import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class PlanetaryProjectiles {
	
	/*	Write the body of this method
	*	args is the information about the system
	*	it is in the order specified in the problem
	*/
	// number of planets, x y r (n times), x y vx vy
	public static String collide(double[] args) {
		// calculate if stuff collides
		double planetCount = args[0];
		ArrayList<Vector> planets = new ArrayList<Vector>();
		for (int planet = 0; planet < (int)planetCount; planet++) {
			Vector vec = new Vector(args[(planet * 3) + 1] - args[args.length - 4], args[(planet * 3) + 2] - args[args.length - 3]);
			planets.add(vec);
		}
		Vector velocity = new Vector(args[args.length - 2], args[args.length - 1]);
		double shortestProjection = 100000000;
		int shortestPlanet = -1;
		for (int i = 0; i < planets.size(); i++) {
			double radius = args[(i * 3) + 3];
			Vector planet = planets.get(i);
			Vector projection = velocity.scale(velocity.dot(planet)/velocity.dot(velocity));
			if (velocity.dot(planet)/velocity.dot(velocity) < 0) continue;
			Vector error = planet.add(projection.scale(-1));
			if (Math.sqrt(error.dot(error)) < radius) {
				double projLen = Math.sqrt(projection.dot(projection));
				if (projLen < shortestProjection || shortestPlanet < 0) {
					shortestProjection = projLen;
					shortestPlanet = i;
				}
			}
		}
		if (shortestPlanet == 0) return "HIT EARTH";
		if (shortestPlanet > 0) return "HIT PLANET";
		return "MISS";
	}

	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("PlanetaryProjectiles.txt"));
		while(br.ready()){
			String[] line = br.readLine().split("\\s");
			double[] doubleData = new double[line.length];
			for (int i = 0; i < line.length; i++)
				doubleData[i] = Double.parseDouble(line[i]);
			System.out.println(collide(doubleData));
		}
		br.close();
	}
	

}