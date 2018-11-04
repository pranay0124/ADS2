import java.util.Arrays;
class PageRank {
	private Digraph pagerankGraph;
	private double[] values;
	private double[] finalvalues;
	double test;
	// private int ver = pagerankGraph.V();
	PageRank(Digraph graph) {
		this.pagerankGraph = graph;
		values = new double[pagerankGraph.V()];
		for (int i = 0; i < pagerankGraph.V(); i++) {
			// System.out.println(pagerankValue[i] + "before");
			values[i] = (1.0 / (double)(pagerankGraph.V()));
			// System.out.println(pagerankValue[i] + "after");
		}
		finalvalues = new double[pagerankGraph.V()];
		updateValue();
	}

	void updateValue() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < pagerankGraph.V(); j++) {
				test = 0.0;
				for (int each : pagerankGraph.reverse().adj(j)) {
					// System.out.println(test + " before");
					test = test + ((double)values[each]) / (double)(pagerankGraph.outdegree(each));
					// System.out.println(test + " after");
				}
				finalvalues[j] = test;
			}
			if(Arrays.equals(values, finalvalues)) {
				break;
			} else {
				values = finalvalues.clone();
			}
		}
		for (int k = 0; k < finalvalues.length; k++) {
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
		String[] vertex;
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		// int verticescopy = vertices;
		Digraph digraph = new Digraph(vertices);
		Digraph digraphextra = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			vertex = StdIn.readLine().split(" ");
			if (vertex.length >= 2) {
				for (int j = 1; j < vertex.length; j++) {
					digraph.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt(vertex[j]));
					digraphextra.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt(vertex[j]));
				}
			} else {
				for(int k=0;k<vertices;k++) {
					if(k==i) {
						continue;
					} else {
						digraphextra.addEdge(Integer.parseInt(vertex[0]),k);
					}
				}
			}
		}
		System.out.println(digraph);

		// Create page rank object and pass the graph object to the constructor*/
		PageRank pagerank = new PageRank(digraph);

		// print the page rank object

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
