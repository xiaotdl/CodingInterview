package evaluate_division;

import java.util.*;

/**
 * Created by Xiaotian on 2/23/18.
 */
public class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String u = equations[i][0];
            String v = equations[i][1];
            double w = values[i];
            graph.putIfAbsent(u, new HashMap<String, Double>());
            graph.putIfAbsent(v, new HashMap<String, Double>());
            graph.get(u).put(v, w);
            graph.get(v).put(u, 1 / w);
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < res.length; i++) {
            String u = queries[i][0];
            String v = queries[i][1];
            res[i] = bfs(graph, u, v);
        }
        return res;
    }

    private double bfs(Map<String, Map<String, Double>> graph, String src, String dst) {
        if (graph.get(src) == null || graph.get(dst) == null) return -1;

        Queue<String> q = new LinkedList<>();
        Map<String, Double> dist = new HashMap<>(); // dist to src
        Set<String> visited = new HashSet<>();

        q.offer(src);
        dist.put(src, 1d); // src/src = 1d
        visited.add(src);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String u = q.poll();
                for (Map.Entry<String, Double> edge : graph.get(u).entrySet()) {
                    String v = edge.getKey();
                    double w = edge.getValue();

                    if (!visited.contains(v)) {
                        visited.add(v);
                        q.add(v);
                    }

                    dist.put(v, dist.get(u) * w);
                    if (v.equals(dst)) return dist.get(v);
                }
            }
        }
        return -1;
    }
}
