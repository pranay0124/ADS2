import java.util.Scanner;
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = scan.nextInt();
		int edges = scan.nextInt();
		EdgeWeightedGraph edgewtGraph = new EdgeWeightedGraph(vertices);
		while (scan.hasNext()) {
			String[] tokens = scan.nextLine().split(" ");
			edgewtGraph.addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
		}
		KruskalMST kruskal = new KruskalMST(edgewtGraph);
		System.out.format("%.5f", kruskal.weight());
	}
}
