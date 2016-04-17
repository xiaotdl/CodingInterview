/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Rehashing {
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
        int size = hashTable.length;
        int newSize = 2 * size;
        ListNode[] newHashTable = new ListNode[newSize];

        for (int i = 0; i < size; i++) {
            ListNode n1 = hashTable[i];
            while (n1 != null) {
                ListNode n2 = n1;
                n1 = n1.next;
                n2.next = null;

                // Insert into new hashtable
                int val = (n2.val % newSize + newSize) % newSize;
                if (newHashTable[val] == null) {
                    newHashTable[val] = n2;
                } else {
                    ListNode tail = newHashTable[val];
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = n2;
                }
            }
        }

        return newHashTable;
    }
};
