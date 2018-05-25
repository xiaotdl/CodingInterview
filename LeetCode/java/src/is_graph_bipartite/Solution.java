package is_graph_bipartite;

import java.util.*;

/**
 * Created by Xiaotian on 2/17/18.
 */
public class Solution {
    // TLE
    // tag: brutal force
    // time: O(2^n)
    // space: O(1)
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        // iterate all possible two independent subsets
        for (int k = 0; k < power(2, graph.length); k++) {
            if (check(graph, k)) return true;
        }
        return false;
    }

    private boolean check(int[][] graph, int k) {
        Set<String> seen = new HashSet<>();
        // iterate all edges
        for (int i = 0; i < graph.length; i++) { // from
            for (int j = 0; j < graph[i].length; j++) { // to
                int from = i;
                int to = graph[i][j];
                if (seen.contains(from+","+to) || seen.contains(to+","+from)) continue;
                seen.add(from+","+to);
                int a = ((k >> from) & 1);
                int b = ((k >> to) & 1);
                if (a + b != 1) return false;
            }
        }
        return true;
    }

    private int power(int x, int y) {
        int product = 1;
        for (int i = 1; i <= y; i++) {
            product *= x;
        }
        return product;
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/is-graph-bipartite/solution/
    // tag: dfs
    // time: O(n^2)
    // space: O(n)
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // -1: uncolored, 0: red, 1: blue
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) { // This graph might be a disconnected graph. Need to check each unvisited/uncolored node.
            if (colors[i] != -1) continue;
            if (!dfs(graph, colors, i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int u, int color) {
        if (colors[u] != -1) return colors[u] == color;
        colors[u] = color;

        int[] neighbors = graph[u];
        for (int v : neighbors) {
            if (!dfs(graph, colors, v, color == 0 ? 1 : 0)) {
                return false;
            }
        }
        return true;
    }
}
