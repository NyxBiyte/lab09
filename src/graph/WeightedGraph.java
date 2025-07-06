package graph;

import java.util.*;
import java.lang.*;

public class WeightedGraph {
    private final Map<String, Map<String, Integer>> adjList = new HashMap<>();

    public List<String> getNeighbors(String vertex) {
        return new ArrayList<>(adjList.getOrDefault(vertex, Map.of()).keySet());
    }

    public int getWeight(String start, String end) {
        return adjList.getOrDefault(start, Map.of()).getOrDefault(end, Integer.MAX_VALUE);
    }

    public Set<String> getVertices() {
        return adjList.keySet();
    }

    public void addEdge(String start, String end, int weight) {
        if (!adjList.containsKey(start)){
            adjList.put(start, new HashMap<>());
        }
        adjList.get(start).put(end, weight);
    }

}