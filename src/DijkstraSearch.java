import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {

    private Map<Vertex<V>, Double> distTo; // shortest known distance from start

    public DijkstraSearch(WeightedGraph<V> graph, V startData) {
        super(graph.getVertex(startData));
        this.distTo = new HashMap<>();
        search();
    }

    @Override
    public void search() {
        // Initialize all distances to infinity
        distTo.put(start, 0.0);

        // Min-heap: vertex with smallest known distance is processed first
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
                Comparator.comparingDouble(v -> distTo.getOrDefault(v, Double.MAX_VALUE))
        );
        pq.add(start);
        marked.put(start, true);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            double currentDist = distTo.getOrDefault(current, Double.MAX_VALUE);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double    weight   = entry.getValue();
                double    newDist  = currentDist + weight;

                if (newDist < distTo.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current); // better path found
                    marked.put(neighbor, true);
                    pq.add(neighbor);              // re-add with updated priority
                }
            }
        }
    }

    public double distanceTo(Vertex<V> target) {
        return distTo.getOrDefault(target, Double.MAX_VALUE);
    }
}
