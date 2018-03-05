package reconstruct_itinerary;

import java.util.*;

/**
 * Created by Xiaotian on 3/2/18.
 */
public class Solution {
    // tag: dfs
    // time: O(V+E)
    // space: O(V+E)
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> graph = new HashMap<>(); // from2tos
        for (String[] t : tickets) {
            String u = t[0];
            String v = t[1];
            graph.putIfAbsent(u, new ArrayList<String>());
            graph.get(u).add(v);
        }
        for (String u : graph.keySet()) {
            List<String> nexts = graph.get(u);
            Collections.sort(nexts);
        }

        List<String> path = new ArrayList<>();
        path.add("JFK");
        dfs(graph, "JFK", tickets.length, path);
        return path;
    }

    private boolean dfs(Map<String, List<String>> graph, String u, int totalTicketCnt, List<String> path) {
        if (path.size() == totalTicketCnt + 1) return true;
        if (!graph.containsKey(u)) return false;

        List<String> nexts = graph.get(u);
        for (int i = 0; i < nexts.size(); i++) {
            String v = nexts.get(i);
            path.add(v);
            nexts.remove(i);
            if (dfs(graph, v, totalTicketCnt, path)) return true;
            path.remove(path.size() - 1);
            nexts.add(i, v);
        }
        return false;
    }
}
