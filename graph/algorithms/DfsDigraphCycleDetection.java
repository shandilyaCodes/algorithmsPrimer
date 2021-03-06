import java.util.ArrayList;
import java.util.List;

/**
 * The algorithm to find cycle in digraph is different than the one used in the undirected graph
 * Here we maintain two visited arrays, one for global visited another for local visited dfs
 * More details on this topic here in this video : https://www.youtube.com/watch?v=uzVUw90ZFIg&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw
 */
public class DfsDigraphCycleDetection {
    public static void main(String[] args) {
        List<List<Integer>> digraphWithCycle = createDigraphWithCycle();
        List<List<Integer>> digraphWithoutCycle = createDigraphWithoutCycle();

        if (isCyclic(9, digraphWithCycle)) {
            System.out.println("Digraph is cyclic");
        } else {
            System.out.println("Digraph is acyclic");
        }

        if (isCyclic(9, digraphWithoutCycle)) {
            System.out.println("Digraph is cyclic");
        } else {
            System.out.println("Digraph is acyclic");
        }
    }

    // Checks if a particular component in the graph has cycle or not using DFS traversal
    private static boolean checkCycle(int node, List<List<Integer>> adj, int[] visited, int[] dfsVisited) {
        visited[node] = 1;
        dfsVisited[node] = 1;

        for (Integer i : adj.get(node)) {
            if (visited[i] == 0) {
                if (checkCycle(i, adj, visited, dfsVisited)) {
                    return true;
                }
            } else if (dfsVisited[i] == 1) {
                return true; // When both the visited & dfs visited array at a point become 1 it means cycle is detected
            }
        }
        dfsVisited[node] = 0; // At the last if cycle isn't detected we mark the value in dfsVisited to be 0
        return false;
    }

    // Iterates and checks over all the components for cycle in the graph
    private static boolean isCyclic(int vertices, List<List<Integer>> adj) {
        int[] visited = new int[vertices];
        int[] dfsVisited = new int[vertices];

        for (int i = 0 ; i < vertices ; i++) {
            if (visited[i] == 0) {
                if (checkCycle(i, adj, visited, dfsVisited))
                    return true;
            }
        }
        return false;
    }

    private static List<List<Integer>> createDigraphWithCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 5, 4);
        addEdge(adj, 6, 1);
        addEdge(adj, 6, 7);
        addEdge(adj, 7, 8);
        addEdge(adj, 8, 6);

        return adj;
    }

    private static List<List<Integer>> createDigraphWithoutCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 5, 4);
        addEdge(adj, 6, 1);
        addEdge(adj, 6, 7);
        addEdge(adj, 6, 8);
        addEdge(adj, 7, 8);

        return adj;
    }

    private static void addEdge(List<List<Integer>> adj, Integer from, Integer to) {
        adj.get(from).add(to);
    }
}
