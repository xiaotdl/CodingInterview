package bidirectional_bfs;

import java.util.*;

/**
 * Created by Xiaotian on 4/5/18.
 */
class T {}

class Node {
    private final T data;
    private final Set<Node> adjacent = new HashSet<Node>();

    public Set<Node> getAdjacent() {
        return adjacent;
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    // returns if the node was added, false if already there
    public boolean addAdjacent(Node node) {
        return adjacent.add(node);
    }

    // returns true if any were added
    public boolean addAdjacents(Set<Node> nodes) {
        return adjacent.addAll(nodes);
    }
}

public class Solution {
    // credit: https://stackoverflow.com/questions/38674659/bidirectional-search-implementation-for-graph
    // credit: https://www.geeksforgeeks.org/bidirectional-search/
    // BFS time: (e^d), e: avg edge numbers, d: distance from source to target
    // Bi-directional BFS time: (e^(d/2))
    public static boolean pathExistsBidirectional(Node a, Node b) {
        // BFS on both nodes at the same time
        Queue<Node> queueA = new LinkedList<>();
        Queue<Node> queueB = new LinkedList<>();
        Set<Node> visitedA = new HashSet<>();
        Set<Node> visitedB = new HashSet<>();

        visitedA.add(a);
        visitedB.add(b);
        queueA.add(a);
        queueB.add(b);

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            if (pathExistsBidirectionalHelper(queueA, visitedA, visitedB)) {
                return true;
            }
            if (pathExistsBidirectionalHelper(queueB, visitedB, visitedA)) {
                return true;
            }
        }

        return false;
    }

    private static boolean pathExistsBidirectionalHelper(Queue<Node> queue, Set<Node> visitedFromThisSide, Set<Node> visitedFromThatSide) {
        if (!queue.isEmpty()) {
            Node next = queue.remove();
            for (Node adjacent : next.getAdjacent()) {
                if (visitedFromThatSide.contains(adjacent)) {
                    return true;
                } else if (visitedFromThisSide.add(adjacent)) {
                    queue.add(adjacent);
                }
            }
        }
        return false;
    }
}
