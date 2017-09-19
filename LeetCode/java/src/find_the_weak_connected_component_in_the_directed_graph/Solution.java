package find_the_weak_connected_component_in_the_directed_graph;

import java.util.*;

/**
 * Created by Xiaotian on 9/19/17.
 */
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
};

public class Solution {
    // tag: graph, union find
    // time: O(n)
    // space: O(n)
    class UnionFind {
        Map<Integer, Integer> parents;

        public UnionFind(Set<Integer> set) {
            parents = new HashMap<>();
            for (int x : set) {
                parents.put(x, x);
            }

        }

        public int find(int x) {
            if (parents.get(x) == x) return x;
            parents.put(x, find(parents.get(x)));
            return parents.get(x);
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parents.put(rootA, rootB);
            }
        }
    }
    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) return res;

        Set<Integer> set = new HashSet<>(); // node labels
        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
        }
        UnionFind uf = new UnionFind(set);

        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                uf.union(node.label, neighbor.label);
            }
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // rootNodeLabel2nodeLabels
        for (DirectedGraphNode node : nodes) {
            int root = uf.find(node.label);
            if (!map.containsKey(root)) {
                map.put(root, new ArrayList<Integer>());
            }
            map.get(root).add(node.label);
        }

        for (Map.Entry<Integer, ArrayList<Integer>> e : map.entrySet()) {
            ArrayList<Integer> groupedNodes = e.getValue();
            Collections.sort(groupedNodes);
            res.add(groupedNodes);
        }
        return res;
    }
}

