/**
 * @author : Pranay Kumar Y.
 * Date : 10th November,2018.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() { }
	/**
	 * Main Function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
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
			// Handle the case of DirectedPaths,
			//                     where two integers are given.
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
			// First is the source and second is the via is the one where
			//                                    path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] paths = scan.nextLine().split(" ");
			int source = Integer.parseInt(paths[0]);
			int via = Integer.parseInt(paths[1]);
			int destination = Integer.parseInt(paths[2]);
			String str = "";

			DijkstraUndirectedSP sp1 =
			    new DijkstraUndirectedSP(ewg, source);
			if (sp1.hasPathTo(destination)) {
				// str1 = sp1.pathTo(d1).toString();
				Queue<Integer> queue = new Queue<Integer>();
				for (Edge edge : sp1.pathTo(via)) {
					int vertex = edge.either();
					int other = edge.other(vertex);
					int count1 = 0;
					int count2 = 0;
					for (Integer i : queue) {
						if (vertex == i) {
							count1 = 1;
						}
						if (other == i) {
							count2 = 1;
						}
					}
					if (count2 == 0) {
						queue.enqueue(other);
					}
					if (count1 == 0) {
						queue.enqueue(vertex);
					}
				}
				DijkstraUndirectedSP sp2 =
				    new DijkstraUndirectedSP(ewg, via);
				for (Edge edge : sp2.pathTo(destination)) {
					int vertex = edge.either();
					int other = edge.other(vertex);
					int count1 = 0;
					int count2 = 0;
					for (Integer i : queue) {
						if (vertex == i) {
							count1 = 1;
						}
						if (other == i) {
							count2 = 1;
						}

					}
					if (count1 == 0) {
						queue.enqueue(vertex);
					}
					if (count2 == 0) {
						queue.enqueue(other);
					}
				}
				System.out.println(sp1.distTo(via)
				                   + sp2.distTo(destination));
				while (!queue.isEmpty()) {
					str += queue.dequeue() + " ";
				}
				System.out.print(str.trim());
			} else {
				System.out.println("No Path Found.");
			}
			// System.out.println(dist1 + dist2);
			break;

		default:
			break;
		}

	}
}

