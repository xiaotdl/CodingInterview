package clone_graph;

import java.util.*;

/**
 * Created by Xiaotian on 2/9/18.
 */
class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};


public class Solution {
    // 1. BFS从1个点找到所有点
    // 2. 复制所有的点，并且记录到HASH里
    // 3. 复制所有的边，利用HASH
    // tag: bfs, hash
    // time: O(n)
    // space: O(n)
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        List<UndirectedGraphNode> nodes = bfs(node);

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>(); // oldNode2newNode
        for (UndirectedGraphNode _node : nodes) {
            map.put(_node, new UndirectedGraphNode(_node.label));
        }

        for (UndirectedGraphNode _node : nodes) {
            UndirectedGraphNode newNode = map.get(_node);
            for (UndirectedGraphNode neighbor : _node.neighbors) {
                newNode.neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    private List<UndirectedGraphNode> bfs(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        Set<UndirectedGraphNode> seen = new HashSet<>();
        q.offer(node);
        seen.add(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode curr = q.poll();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    q.offer(neighbor);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(seen);
    }
}
