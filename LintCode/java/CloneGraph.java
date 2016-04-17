/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */

    // V1, O(n), O(n)
    // HashMap, BFS(Queue)
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        // Clone nodes
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));

        int start = 0;
        while (start < nodes.size()) {
            UndirectedGraphNode curr = nodes.get(start++);
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    nodes.add(neighbor);
                }
            }
        }

        // Clone neighbors
        for (UndirectedGraphNode curr : nodes) {
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}

