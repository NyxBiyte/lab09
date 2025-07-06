package graph;

import java.io.*;
import java.lang.*;

public class GraphReader {
    public static WeightedGraph readGraphFromFile(String filePath) throws IOException {
        WeightedGraph graph = new WeightedGraph();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" "); //splits station from connections
            String start = parts[0];

            for (int i = 1; i < parts.length; i++) {
                String[] edge = parts[i].split(","); //splits into connected station from travel time
                String end = edge[0];
                int weight = Integer.parseInt(edge[1]);
                graph.addEdge(start, end, weight);

            }
        }
        return graph;
    }
}
