import java.util.Scanner;
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph edgewtGraph = new EdgeWeightedGraph(vertices);
		while (scan.hasNext()) {
			String[] tokens = scan.nextLine().split(" ");
			edgewtGraph.addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
		}
		KruskalMST kruskal = new KruskalMST(edgewtGraph);
		System.out.format("%.5f", kruskal.weight());
	}
}
