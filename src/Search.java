import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Search<V> {
    protected Vertex<V> start;
    protected Map<Vertex<V>, Vertex<V>> edgeTo;  // vertex -> how we got there (parent)
    protected Map<Vertex<V>, Boolean>   marked;  // visited set

    public Search(Vertex<V> start) {
        this.start  = start;
        this.edgeTo = new HashMap<>();
        this.marked = new HashMap<>();
    }

    public abstract void search();

    public boolean hasPathTo(Vertex<V> target) {
        return Boolean.TRUE.equals(marked.get(target));
    }

    public List<V> pathTo(Vertex<V> target) {
        if (!hasPathTo(target)) return new LinkedList<>();

        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> v = target; v != start; v = edgeTo.get(v)) {
            path.addFirst(v.getData());
        }
        path.addFirst(start.getData());
        return path;
    }
}