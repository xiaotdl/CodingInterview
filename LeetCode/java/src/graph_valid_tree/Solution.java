package graph_valid_tree;

import java.util.*;

/**
 * Created by Xiaotian on 9/18/17.
 */
public class Solution {
    // 一个<不含任何回路>的<连通图>称为树
    // a valid tree requires:
    //   1. all vertices are connected
    //   2. no circle
    // tag: bfs
    // time: O(n)
    // space: O(n)
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (edges.length != n - 1) return false;

        Map<Integer, Set<Integer>> graph = initGraph(n, edges);  //node2neighbors

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();
        q.offer(0);
        visitedNodes.add(0);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (Integer neighbor : graph.get(node)) {
                if (visitedNodes.contains(neighbor)) continue; // prevent from going back and create a loop
                q.offer(neighbor);
                visitedNodes.add(neighbor);
            }
        }
        return visitedNodes.size() == n; // n nodes + n - 1 edges meaning all nodes connected and no circle
    }

    private Map<Integer, Set<Integer>> initGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}}));
    }
}

class SolutionII {
    // tag: union find
    // time: O(n)
    // space: O(n)
    class UnionFindSet {
        int[] parents;

        UnionFindSet(int n) {
            parents = new int[n + 1];
            for (int x = 1; x <= n; x++) {
                parents[x] = x;
            }
        }

        public int find(int x) {
            if (parents[x] == x) return x;
            parents[x] = find(parents[x]);
            return parents[x];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parents[rootA] = rootB;
            }
        }
    }
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (edges.length != n - 1) return false; // ensure no circle

        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (ufs.find(u) == ufs.find(v)) { // n - 1 edges and no circle meaning all nodes connected
                return false;
            }
            ufs.union(u, v);
        }
        return true;
    }
}
