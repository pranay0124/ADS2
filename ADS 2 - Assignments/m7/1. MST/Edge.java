/**
 *  Class for edge.
 *  The Edge class represents a weighted edge in an
 *  EdgeWeightedGraph. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the edge and
 *  the weight. The natural order for this data type is by
 *  ascending order of weight.
 */
public class Edge implements Comparable<Edge> {
    /**
     * { variable for vertex v }.
     */
    private final int v;
    /**
     * { variable for vertex w }.
     */
    private final int w;
    /**
     * { variable for weight }.
     */
    private final double weight;

    /**
     * Constructs the object.
     *
     * @param      v1       { parameter_description }
     * @param      w1       { parameter_description }
     * @param      weight1  The weight
     */
    public Edge(final int v1, final int w1, final double weight1) {
        if (v1 < 0) {
            throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        }
        if (w1 < 0) {
            throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        }
        if (Double.isNaN(weight1)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = v1;
        this.w = w1;
        this.weight = weight1;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    /**
     * Compares two edges by weight.
     *
     * @param      that  The that
     *
     * @return     { description_of_the_return_value }
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    // /**
    //  * Unit tests the {@code Edge} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     Edge e = new Edge(12, 34, 5.67);
    //     StdOut.println(e);
    // }
}

