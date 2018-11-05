/**
 * @author : Pranay Kumar Y.
 * Date : 5th November,2018.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main FUnction.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgewtGraph = new EdgeWeightedGraph(vertices);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            edgewtGraph.addEdge(new Edge(Integer.parseInt(tokens[0]),
                                         Integer.parseInt(tokens[1]),
                                         Double.parseDouble(tokens[2])));
        }
        KruskalMST kruskal = new KruskalMST(edgewtGraph);
        System.out.format("%.5f", kruskal.weight());
    }
}

