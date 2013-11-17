import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindTheGeneral {
	
	static int destId = 0;
	static List<Asteroid> asteroids;
	
	public static Asteroid getAst(int id) {
		for (Asteroid a : asteroids) {
			if (a.id == id) return a;
		}
		return null;
	}
	
	public static class Asteroid {
		int id;
		boolean isAlien;
		List<Integer> adjacentAsteroids;
		
		public Asteroid (int id, boolean isAlien, List<Integer> adjacent){
			this.id = id;
			this.isAlien = isAlien;
			this.adjacentAsteroids = adjacent;
		}
		public boolean equals(Asteroid a) {
			return a.id == this.id;
		}
	}
	
	public static class Node {
		int depth = 0;
		ArrayList<Integer> past;
		Asteroid a;
		public Node(Asteroid a, int depth) {
			this.a = a;
			this.depth = depth;
		}
		public void expand(ArrayList<Node> asts, ArrayList<Node> vis) {
			for (Integer id : this.a.adjacentAsteroids) {
				Asteroid anAst = getAst(id.intValue());
				if (anAst.isAlien) continue;
				Node newNode = new Node(anAst, this.depth + 1);
				if (!vis.contains(newNode)) {
					vis.add(newNode);
					asts.add(newNode);
				}
			}
		}
		public boolean equals(Node n) {
			return n.a.equals(this.a);
		}
	}
	
	/* Write the body for this method
	 * source is the asteroid you're on
	 * target is the asteroid you want to go to
	 * asteroids is a List of all the Asteroid objects
	 */
	public static int fastestRouteToGeneral (Asteroid source, Asteroid target, List<Asteroid> _asteroids){
		asteroids = _asteroids;
		destId = target.id;
		ArrayList<Node> asts = new ArrayList<Node>();
		ArrayList<Node> vis = new ArrayList<Node>();
		asts.add(new Node(source, 0));
		while (true) {
			int a = iterateSearch(asts, vis);
			if (a >= 0) return a;
			if (a == -1) return -1;
		}
	}
	
	public static int iterateSearch(ArrayList<Node> asts, ArrayList<Node> vis) {
		if (asts.size() == 0) return -1;
		Node node = asts.remove(0);
		if (node.a.id == destId) return node.depth;
		node.expand(asts, vis);
		return -2;
	}
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader ("FindTheGeneral.txt"));
		while (br.ready()) {
			List<Asteroid> asteroids = new ArrayList<Asteroid>();
			String line = br.readLine();
			line = line.trim();
			String[] sourceTarget = line.split(",");
			int source = Integer.parseInt(sourceTarget[0]);
			int target = Integer.parseInt(sourceTarget[1]);
			Asteroid sourceNode = null;
			Asteroid targetNode = null;
			line = br.readLine();
			while (line.length() != 1 && (! line.equals("="))) {
				String[] inputArray = line.split(",");
				int id = Integer.parseInt(inputArray[0]);
				boolean isAlien = Boolean.parseBoolean(inputArray[1]);
				List<Integer> adjacent = new ArrayList<Integer> ();
				for (int i = 2; i < inputArray.length; i++) {
					adjacent.add(new Integer(inputArray[i]));
				}
				Asteroid current = new Asteroid (id, isAlien, adjacent);
				asteroids.add(current);
				if (id == source) {
					sourceNode = current;
				}
				else if (id == target) {
					targetNode = current;
				}
				line = br.readLine();
			}
			System.out.println (fastestRouteToGeneral  (sourceNode, targetNode, asteroids));
		}
		br.close();
	}

}
