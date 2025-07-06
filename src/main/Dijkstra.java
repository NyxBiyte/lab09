package main;

import java.util.*;
import java.lang.*;
import graph.*;

public class Dijkstra {
    private final WeightedGraph graph;
    private final Map<String, String> visited = new HashMap<>();

    public Dijkstra(WeightedGraph graph) {
        this.graph = graph;
    }

    public Map<String, Integer> shortestPath(String start) {
        Map<String, Integer> weights = new HashMap<>();
        PriorityQueue<String> edges = new PriorityQueue<>(Comparator.comparingInt(weights::get)); // sorted by least weight
        Set<String> visited = new HashSet<>();

        for (String vertices : graph.getVertices()) {
            weights.put(vertices, Integer.MAX_VALUE);
        }

        weights.put(start, 0);
        edges.add(start);

        while (!edges.isEmpty()) {
            String current = edges.poll();

            if (!visited.contains(current)) {
                visited.add(current);
                for (String neighbor : graph.getNeighbors(current)) {
                    int newWeight = weights.get(current) + graph.getWeight(current, neighbor);
                    if (newWeight < weights.get(neighbor)) {
                        weights.put(neighbor, newWeight);
                        this.visited.put(neighbor, current);
                        edges.add(neighbor);
                    }
                }
            }


        }

        return weights;
    }

    public List<String> getShortestPath(String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null && !current.equals(start)) {
            path.add(current);
            current = visited.get(current);
        }

        if (current == null) {
            return Collections.emptyList();
        }else {
            path.add(start);
            Collections.reverse(path);
            return path;
        }
    }
}
