/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */
public class AddTwoNumbers {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    
    // V1, O(n)
    public ListNode addLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        
        
        int more = 0;
        int sum;
        ListNode head = null;
        ListNode tail = new ListNode(0);
        
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                sum = l2.val + more;
            } else if (l2 == null) {
                sum = l1.val + more;
            } else {
                sum = l1.val + l2.val + more;
            }
            more = 0;
            if (sum >= 10) {
                sum = sum % 10;
                more = 1;
            }
            ListNode node = new ListNode(sum);
            if (head == null) {
                head = node;
            }
            tail.next = node;
            tail = tail.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        
        if (more == 1) {
            tail.next = new ListNode(1);
        }
        
        return head;
    }
}
