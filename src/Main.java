import java.util.List;

public class Main {

    public static void main(String[] args) {
        //build the graph
        WeightedGraph<String> graph = new WeightedGraph<>(false); // directed

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "D", 2);
        graph.addEdge("B", "E", 3);
        graph.addEdge("C", "D", 5);
        graph.addEdge("D", "E", 1);

        //bfs from A
        System.out.println("=== Breadth-First Search from A ===");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, "A");

        String[] targets = {"B", "C", "D", "E"};
        for (String t : targets) {
            Vertex<String> target = graph.getVertex(t);
            if (bfs.hasPathTo(target)) {
                List<String> path = bfs.pathTo(target);
                System.out.println("Path A -> " + t + " : " + path);
            } else {
                System.out.println("No path to " + t);
            }
        }

        System.out.println();

        //djkstra from A
        System.out.println("=== Dijkstra Search from A ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, "A");

        for (String t : targets) {
            Vertex<String> target = graph.getVertex(t);
            if (dijkstra.hasPathTo(target)) {
                List<String> path     = dijkstra.pathTo(target);
                double       distance = dijkstra.distanceTo(target);
                System.out.printf("Shortest path A -> %s : %s  (cost = %.1f)%n", t, path, distance);
            } else {
                System.out.println("No path to " + t);
            }
        }

        /*
         * Expected Dijkstra results:
         *   A -> B : [A, B]       cost = 1.0
         *   A -> C : [A, C]       cost = 4.0
         *   A -> D : [A, B, D]    cost = 3.0   (not A->C->D which is 9)
         *   A -> E : [A, B, D, E] cost = 4.0   (not A->B->E which is 4 too — tie)
         */
    }
}
