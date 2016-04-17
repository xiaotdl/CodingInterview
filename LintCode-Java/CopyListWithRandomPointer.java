/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class CopyListWithRandomPointer {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */

    // V1, O(n), O(n)
    // HashMap
    // Iterate twice
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy1 = new RandomListNode(0);
        RandomListNode dummy2 = new RandomListNode(0);
        RandomListNode head1 = head, head2;
        dummy1.next = head1;
        RandomListNode prev = dummy2;


        while (head1 != null) {
            head2 = new RandomListNode(head1.label);
            map.put(head1, head2);

            prev.next = head2;
            prev = prev.next;
            head1 = head1.next;
        }

        head1 = dummy1.next;
        head2 = dummy2.next;
        while (head1 != null) {
            head2.random = map.get(head1.random);
            head1 = head1.next;
            head2 = head2.next;
        }

        return dummy2.next;
    }

    // V2, O(n), O(n)
    // HashMap
    // Iterate once
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode prev = dummy, newNode;

        while (head != null) {
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }

            prev.next = newNode;

            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }

            prev = prev.next;
            head = head.next;
        }

        return dummy.next;
    }

    // V3, O(n), O(1)
    // no hashmap/additional space version
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }
    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }
    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode tmp = head.next;
            head.next = tmp.next;
            head = head.next;
            if (tmp.next != null) {
                tmp.next = tmp.next.next;
            }
        }
        return newHead;
    }
}

