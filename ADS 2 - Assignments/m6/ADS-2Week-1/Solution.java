
class PageRank {
	private Digraph pagerankGraph;
	private double[] values;
	private double[] finalvalues;
	// private int ver = pagerankGraph.V();
	PageRank(Digraph graph) {
		this.pagerankGraph = graph;
		values = new double[pagerankGraph.V()];
		finalvalues = new double[pagerankGraph.V()];
		for (int i = 0; i < pagerankGraph.V(); i++) {
			// System.out.println(pagerankValue[i] + "before");
			values[i] = (1.0 / (double)(pagerankGraph.V()));
			// System.out.println(pagerankValue[i] + "after");
		}
		updateValue();
	}
	Digraph tempgraph = pagerankGraph;
	void updateValue() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; i < pagerankGraph.V(); j++) {
				double test = 0.0;
				for (Integer each : tempgraph.adj(j)) {
					System.out.println(test + " before");
					test += (values[each]) / (double)(pagerankGraph.outdegree(each));
					System.out.println(test + " after");
				}
				finalvalues[j] = test;
			}
			values = finalvalues.clone();
		}
		for (int k = 0; k < pagerankGraph.V(); k++) {
			System.out.print(k + " : " + finalvalues[k] + "\n");
		}
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		int vertices = Integer.parseInt(StdIn.readLine());
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		// int verticescopy = vertices;
		Digraph digraph = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			String[] vertex = StdIn.readLine().split(" ");
			for (int j = 1; j < vertex.length; j++) {
				digraph.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt(vertex[j]));
			}
		}
		System.out.println(digraph);

		// Create page rank object and pass the graph object to the constructor*/
		PageRank pagerank = new PageRank(digraph);

		// print the page rank object
		System.out.println(pagerank);

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
