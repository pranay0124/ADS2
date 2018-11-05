/**
 * Class for kruskal mst.
 */
public class KruskalMST {
    /**
     * variable for epsilon value.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * variable for weight for MST.
     */
    private double weight;
    /**
     * variable for edges.
     */
    private Queue<Edge> mst = new Queue<Edge>();

    /**
     * Constructs the object.
     *
     * @param      graph     { parameter_description }
     */
    public KruskalMST(final EdgeWeightedGraph graph) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : graph.edges()) {
            pq.insert(e);
        }
        // run greedy algorithm
        UF uf = new UF(graph.numberofVertices());
        while (!pq.isEmpty() && mst.size() < graph.numberofVertices() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }
        // check optimality conditions
        assert check(graph);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a
     * minimum spanning tree (or forest).
     *
     * @return the sum of the edge weights in a
     *         minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }

    /**
     * check optimality conditions.
     *
     * @param      graph     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph graph) {
        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal"
                              + "weight(): %f vs. %f\n", total, weight());
            return false;
        }
        // check that it is acyclic
        UF uf = new UF(graph.numberofVertices());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : graph.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest
        for (Edge e : edges()) {
            // all edges in MST except e
            uf = new UF(graph.numberofVertices());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : graph.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f
                        + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }


    // /**
    //  * Unit tests the {@code KruskalMST} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     In in = new In(args[0]);
    //     EdgeWeightedGraph G = new EdgeWeightedGraph(in);
    //     KruskalMST mst = new KruskalMST(G);
    //     for (Edge e : mst.edges()) {
    //         StdOut.println(e);
    //     }
    //     StdOut.printf("%.5f\n", mst.weight());
    // }

}


