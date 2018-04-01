package copy_list_with_random_pointer;

import java.util.*;

/**
 * Created by Xiaotian on 2/8/18.
 */
class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
};

public class Solution {
    // one pass
    // tag: hash
    // time: O(n)
    // space: O(n)
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>(); // oldNode2newNode

        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newPrev = dummy;
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode newCurr;
            if (!map.containsKey(curr)) {
                newCurr = new RandomListNode(curr.label);
                map.put(curr, newCurr);
            }
            else {
                newCurr = map.get(curr);
            }
            newPrev.next = newCurr;

            RandomListNode newRandom;
            if (!map.containsKey(curr.random)) {
                newRandom = (curr.random != null ? new RandomListNode(curr.random.label) : null);
            }
            else {
                newRandom = map.get(curr.random);
            }
            newCurr.random = newRandom;

            curr = curr.next;
            newPrev = newCurr;
            newCurr = newCurr.next;
        }
        return dummy.next;
    }
}

class SolutionII {
    // two pass
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> m = new HashMap<>(); // old2new

        RandomListNode curr = head;
        while (curr != null) {
            m.put(curr, new RandomListNode(curr.label));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            m.get(curr).next = m.get(curr.next);
            m.get(curr).random = m.get(curr.random);
            curr = curr.next;
        }
        return m.get(head);
    }
}
