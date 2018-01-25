package connected_component_in_undirected_graph;

import java.util.*;

/**
 * Created by Xiaotian on 9/18/17.
 */
class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

public class Solution {
    // tag: union find
    // time: O(n)
    // space: O(n)
    class UnionFindSet {
        Map<Integer, Integer> parents;

        UnionFindSet(Set<Integer> set) {
            parents = new HashMap<>();
            for (Integer i : set) {
                parents.put(i, i);
            }
        }

        public int find (int x) {
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
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> m = new HashMap<>(); // rootNode2nodesInSameComponent

        Set<Integer> set = new HashSet<>();
        for (UndirectedGraphNode node : nodes) {
            set.add(node.label);
        }
        UnionFindSet ufs = new UnionFindSet(set);

        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                ufs.union(node.label, neighbor.label);
            }
        }

        for (UndirectedGraphNode node : nodes) {
            int root = ufs.find(node.label);
            if (!m.containsKey(root)) {
                m.put(root, new ArrayList<Integer>());
            }
            m.get(root).add(node.label);
        }

        for (Map.Entry<Integer, ArrayList<Integer>> e : m.entrySet()) {
            ArrayList<Integer> groupedNodes = e.getValue();
            res.add(groupedNodes);
        }
        return res;
    }
}
