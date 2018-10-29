import java.util.Scanner;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
// class AdjacencyList implements Graph {

// }
class AdjacencyMatrix implements Graph {
	private int edgenum;
	private int[][] adjmatrix;
	private int vertexval;
	AdjacencyMatrix(int vertex) {
		this.vertexval = vertex;
		adjmatrix = new int[vertex][vertex];
		this.edgenum = 0;
	}
	public int V() {
		return this.vertexval;
	}
	public int E() {
		return this.edgenum;
	}
	public void addEdge(int v, int w) {
		if(v == w) {
			return;
		}
		adjmatrix[v][w] = 1;
		adjmatrix[w][v] = 1;
		edgenum++;
	}
	public boolean hasEdge(int v, int w) {
		return adjmatrix[v][w] == 1;
	}
	public Iterable<Integer> adj(int v) {
		return null;
	}
	public String toString() {
		 if(edgenum == 0) {
		 	System.out.println(vertexval + " vertices, " + edgenum + " edges");
		 	System.out.println("No edges");
		 	return null;
		 }
		System.out.println(vertexval + " vertices, " + edgenum + " edges");
		for (int i = 0; i < vertexval; i++) {
			String str = "";
			for (int j = 0; j < vertexval; j++) {
				str = str + adjmatrix[i][j] + " ";
			}
			System.out.println(str);
		}
		return null;
	}
}
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String lineone = scan.nextLine();
		int vertexnum = Integer.parseInt(scan.nextLine());
		int edgenum = Integer.parseInt(scan.nextLine());
		String[] placenames = scan.nextLine().split(",");
		switch (lineone) {
		case "List":
			// AdjacencyList listobj = new AdjacencyList();
			break;
		case "Matrix":
			AdjacencyMatrix matrixobj = new AdjacencyMatrix(vertexnum);
			for (int i = 0; i < edgenum; i++) {
				String[] edges = scan.nextLine().split(" ");
				matrixobj.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[1]));
			}
			matrixobj.toString();
			break;
		}
	}
}