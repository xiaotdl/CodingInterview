package merge_k_sorted_lists;

import java.util.*;

/**
 * Created by Xiaotian on 1/1/17.
 */
// tag: linkedlist, heap
// time: O(nklogk)
// space: O(k)
public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int k = lists.length;
        // min-heap
        // A min-heap is a binary tree such that:
        //     - the data contained in each node is <= the data in that node's children.
        //     - the binary tree is complete.
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(k, new Comparator<ListNode>() {
            @Override
            public int compare (ListNode l1, ListNode l2) {
                return l1.val - l2.val < 0 ? -1 :
                       l1.val - l2.val == 0 ? 0 :
                                              1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node == null) continue;
            heap.add(node);
        }

        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;
            if (tail.next != null) heap.add(tail.next);
        }

        return dummy.next;
    }
}
