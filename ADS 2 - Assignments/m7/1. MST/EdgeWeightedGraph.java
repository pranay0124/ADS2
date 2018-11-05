/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * variable for new line.
     */
    private static final String NEWLINE = System.getProperty(
            "line.separator");
    /**
     * variable for vertices.
     */
    private final int vertices;
    /**
     * variable for edges.
     */
    private int edges;
    /**
     * variable for Bag object.
     */
    private Bag<Edge>[] adj;

    /**
     * Constructs the object.
     * Initializes an empty edge-weighted graph with V vertices and 0 edges.
     *
     * @param      v     { parameter_description }
     */
    public EdgeWeightedGraph(final int v) {
        if (v < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.vertices = v;
        this.edges = 0;
        adj = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    // /**
    //  * Initializes a random edge-weighted graph with {@code V}
    //    vertices and <em>E</em> edges.
    //  *
    //  * @param  V the number of vertices
    //  * @param  E the number of edges
    //  * @throws IllegalArgumentException if {@code V < 0}
    //  * @throws IllegalArgumentException if {@code E < 0}
    //  */
    // public EdgeWeightedGraph(int V, int E) {
    //     this(V);
    //     if (E < 0) throw new IllegalArgumentException(
    //     "Number of edges must be nonnegative");
    //     for (int i = 0; i < E; i++) {
    //         int v = StdRandom.uniform(V);
    //         int w = StdRandom.uniform(V);
    //         double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
    //         Edge e = new Edge(v, w, weight);
    //         addEdge(e);
    //     }
    // }

    // /**
    //  * Initializes an edge-weighted graph from an input stream.
    //  * The format is the number of vertices <em>V</em>,
    //  * followed by the number of edges <em>E</em>,
    //  * followed by <em>E</em> pairs of vertices and edge weights,
    //  * with each entry separated by whitespace.
    //  *
    //  * @param  in the input stream
    //  * @throws IllegalArgumentException if the endpoints of any edge are
    //  not in prescribed range
    //  * @throws IllegalArgumentException if the number of vertices or edges
    //  is negative
    //  */
    // public EdgeWeightedGraph(In in) {
    //     this(in.readInt());
    //     int E = in.readInt();
    //     if (E < 0) throw new IllegalArgumentException(
    //     "Number of edges must be nonnegative");
    //     for (int i = 0; i < E; i++) {
    //         int v = in.readInt();
    //         int w = in.readInt();
    //         validateVertex(v);
    //         validateVertex(w);
    //         double weight = in.readDouble();
    //         Edge e = new Edge(v, w, weight);
    //         addEdge(e);
    //     }
    // }

    /**
     * Initializes a new edge-weighted graph that is a deep copy of G.
     *
     * @param  graph the edge-weighted graph to copy
     */
    public EdgeWeightedGraph(final EdgeWeightedGraph graph) {
        this(graph.numberofVertices());
        this.edges = graph.numberofEdges();
        for (int v = 0; v < graph.numberofVertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : graph.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int numberofVertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int numberofEdges() {
        return edges;
    }

    /**
     * throw an IllegalArgumentException unless 0 <= v < V.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("vertex " + v + " is not"
                                               + "between 0 and " + (vertices - 1));
        }
    }

    /**
     * Adds the undirected edge e to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints
     *                                  are between 0 and v-1.
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        edges++;
    }

    /**
     * Returns the edges incident on vertex v.
     *
     * @param  v the vertex
     * @return the edges incident on vertex v as an Iterable
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex v.
     *
     * @param  v the vertex
     * @return the degree of vertex v
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph,
     * use foreach notation:
     * for (Edge e : G.edges()).
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // /**
    //  * Unit tests the {@code EdgeWeightedGraph} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     In in = new In(args[0]);
    //     EdgeWeightedGraph G = new EdgeWeightedGraph(in);
    //     StdOut.println(G);
    // }

}
