package all_paths_from_source_to_target;

import java.util.*;

/**
 * Created by Xiaotian on 3/10/18.
 */
class Solution {
    // tag: dfs
    // time: O(V+E), two loops
    // space: O(V)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;

        Map<Integer, Set<Integer>> m = new HashMap<>(); // u2vs
        for (int i = 0; i < graph.length; i++) {
            int u = i;
            int[] vs = graph[u];
            m.put(u, new HashSet<Integer>());
            for (int v : vs) {
                m.get(u).add(v);
            }
        }
        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        List<Integer> path = new ArrayList<Integer>();
        path.add(0);
        dfs(m, 0, graph.length - 1, visited, path, res);
        return res;
    }

    private void dfs(Map<Integer, Set<Integer>> m, int u, int v, boolean[] visited, List<Integer> path, List<List<Integer>> res) {
        if (u == v) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int _v : m.get(u)) {
            if (visited[_v]) return;
            path.add(_v);
            visited[_v] = true;
            dfs(m, _v, v, visited, path, res);
            visited[_v] = false;
            path.remove(path.size() - 1);
        }
    }
}
