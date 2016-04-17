/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */
public class SortList {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
    using constant space complexity.
     */

    // V1, O(nlogn), O(1)
    // Merge Sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);

        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);

        return merge(left, right);
    }
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        if (head1 != null) {
            curr.next = head1;
        }
        if (head2 != null) {
            curr.next = head2;
        }

        return dummy.next;
    }

    // V2, O(nlogn), O(1)
    // Quick Sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);

        Pair pair = partition(head, mid.val);

        ListNode left = sortList(pair.first);
        ListNode right = sortList(pair.second);

        getTail(left).next = right;

        return left;
    }
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode getTail(ListNode head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
    private Pair partition(ListNode head, int val) {
        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
        ListNode equalDummy = new ListNode(0), equalTail = equalDummy;

        while (head != null) {
            if (head.val < val) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else if (head.val > val) {
                rightTail.next = head;
                rightTail = rightTail.next;
            } else {
                equalTail.next = head;
                equalTail = equalTail.next;
            }
            head = head.next;
        }

        leftTail.next = null;
        rightTail.next = null;
        equalTail.next = null;

        if (leftDummy.next == null && rightDummy.next == null) {
            ListNode mid = findMiddle(equalDummy.next);
            leftDummy.next = equalDummy.next;
            rightDummy.next = mid.next;
            mid.next = null;
        } else if (leftDummy.next == null) {
            leftTail.next = equalDummy.next;
        } else {
            rightTail.next = equalDummy.next;
        }

        return new Pair(leftDummy.next, rightDummy.next);
    }

    class Pair {
        public ListNode first, second;
        public Pair(ListNode first, ListNode second) {
            this.first = first;
            this.second = second;
        }
    }
}

