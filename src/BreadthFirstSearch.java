import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, V startData) {
        super(graph.getVertex(startData));
        search();
    }

    @Override
    public void search() {
        Queue<Vertex<V>> queue = new LinkedList<>();

        marked.put(start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();

                if (!Boolean.TRUE.equals(marked.get(neighbor))) {
                    marked.put(neighbor, true);
                    edgeTo.put(neighbor, current); // remember how we reached neighbor
                    queue.add(neighbor);
                }
            }
        }
    }
}
