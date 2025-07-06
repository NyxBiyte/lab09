package main;

import java.io.*;
import java.util.*;
import java.lang.*;
import graph.*;

public class Main {
    public static void main(String[] args) throws IOException {
        WeightedGraph graph = GraphReader.readGraphFromFile("data/bvg.txt");

        // start: S Schöneweide
        String start = "060192001006";

        // test destinations for lab page
        List<String> destinations = List.of("060192001006", "060068201511", "060066102852", "060053301433", "060120003653");

        Dijkstra dijkstra = new Dijkstra(graph);

        Map<String, Integer> distance = dijkstra.shortestPath(start);
        Map<String, String> names = getStationNames("data/stations.txt");


        for (String station : destinations) {
            int weight = distance.getOrDefault(station, - Integer.MAX_VALUE);
            System.out.println("Ziel: " + station + " (" + names.getOrDefault(station, "Unbekannt") + ")");
            System.out.println("Reisezeit: " + weight/60 + " Minuten");
            List<String> conection = dijkstra.getShortestPath(start, station);
            if (conection.isEmpty()) {
                System.out.println("Kein Pfad gefunden.");
                return;
            }else {
                System.out.println("Kürzester Pfad:");
                for (int i = 0; i < conection.size(); i++) {
                    String id = conection.get(i);
                    String name = names.getOrDefault(id, id);

                    if (i > 0) System.out.print(" -> ");
                    System.out.print(name);
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    private static Map<String, String> getStationNames(String filePath) throws IOException {
        Map<String, String> names = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 2); // splits ID from Name
            if (parts.length == 2) {
                names.put(parts[0], parts[1]);

            }
        }
        return names;
    }
}