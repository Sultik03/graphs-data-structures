import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {

    private Map<V, Vertex<V>> vertices; // data -> Vertex object
    private boolean undirected;

    public WeightedGraph(boolean undirected) {
        this.vertices = new HashMap<>();
        this.undirected = undirected;
    }

    //Adds a vertex for the given data if it doesn't already exist.
    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    /**
     * Adds a weighted edge between source and dest.
     * Creates vertices automatically if they don't exist.
     * Ignores self-loops and parallel edges.
     */
    public void addEdge(V sourceData, V destData, double weight) {
        // Auto-create vertices if not present
        addVertex(sourceData);
        addVertex(destData);

        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest   = vertices.get(destData);

        if (source == dest) return;

        // Ignore parallel edges (already connected)
        if (source.getAdjacentVertices().containsKey(dest)) return;

        source.addAdjacentVertex(dest, weight);

        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public boolean isUndirected() {
        return undirected;
    }
}
