import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
		while (edges > 0) {
			String[] tokens = scan.nextLine().split(" ");
			ewg.addEdge(new Edge(Integer.parseInt(tokens[0]),
			                     Integer.parseInt(tokens[1]),
			                     Integer.parseInt(tokens[2])));
			edges--;
		}
		String query = scan.nextLine();
		switch (query) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(ewg);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] path = scan.nextLine().split(" ");
			int s = Integer.parseInt(path[0]);
			int d = Integer.parseInt(path[1]);
			DijkstraUndirectedSP sp = new DijkstraUndirectedSP(ewg, s);
			if (sp.hasPathTo(d)) {
				System.out.println(sp.distTo(d));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}