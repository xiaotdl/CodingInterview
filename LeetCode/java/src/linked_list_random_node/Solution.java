package linked_list_random_node;

import java.util.*;

/**
 * Created by Xiaotian on 4/26/18.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    // credit: https://leetcode.com/problems/linked-list-random-node/discuss/85662/Java-Solution-with-cases-explain
    // tag: reservoir sampling
    // time: O(n)
    // space: O(1)
    ListNode head;
    Random random;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode curr = head;
        int selectedVal = curr.val;
        curr = curr.next;
        for (int i = 1; curr != null; i++, curr = curr.next) {
            if (random.nextInt(i + 1) == 0) {
                selectedVal = curr.val;
            }
        }
        return selectedVal;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
