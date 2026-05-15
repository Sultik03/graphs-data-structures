import java.util.HashMap;
import java.util.Map;

 // Replaces the old Edge-based approach: instead of Edge storing (source, dest, weight),
public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices; // neighbor -> edge weight

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public V getData() {
        return data;
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public String toString() {
        return "Vertex(" + data + ")";
    }
}
