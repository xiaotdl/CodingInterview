package verify_preorder_serialization_of_a_binary_tree;

import java.util.*;

/**
 * Created by Xiaotian on 3/2/18.
 */
public class Solution {
    // tag: stack
    // time: O(n)
    // space: O(n)
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return false;

        Deque<String> stack = new ArrayDeque<>();
        String[] tokens = preorder.split(",");
        for (String token : tokens) {
            if (token.equals("#")) {
                while (!stack.isEmpty() && stack.peekLast().equals("#")) {
                    stack.pollLast(); // #
                    if (stack.isEmpty()) return false;
                    stack.pollLast(); // parent
                }
                stack.offerLast("#");
            }
            else { // token eq number
                stack.offerLast(token);
            }
        }
        return stack.size() == 1 && stack.peekLast().equals("#");
    }
}

class SolutionII {
    // indegree++, outdegree--, outdegree >= indegree
    // tag: graph
    // time: O(n)
    // space: O(1)
    public boolean isValidSerialization(String preorder) {
        String[] tokens = preorder.split(",");
        int degree = -1;                    // root has no indegree, for compensate init with -1
        for (String token: tokens) {
            degree++;                       // all nodes have 1 indegree (root compensated)
            if (degree > 0) return false;   // total degree should never exceeds 0
            if (!token.equals("#")) {       // only non-leaf node has 2 outdegree
                degree -= 2;
            }
        }
        return degree == 0;
    }
}