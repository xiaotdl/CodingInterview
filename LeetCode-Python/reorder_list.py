# Given a singly linked list L: L0→L1→…→Ln-1→Ln,
# reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

# You must do this in-place without altering the nodes' values.

# For example,
# Given {1,2,3,4}, reorder it to {1,4,2,3}.

# Hide Tags [Linked List]


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return nothing
    def reorderList(self, head):
        # return head if there are less than 3 nodes
        if head == None or head.next == None or head.next.next == None:
            return head
        
        # seperate linked list into two linked lists with same length
        # first one is longer if total length is odd
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        head1 = head
        head2 = slow.next
        slow.next = None
        
        # reverse linked list head2
        dummy = ListNode(0); dummy.next = head2
        p = head2.next; head2.next = None
        while p:
            tmp = p
            p = p.next
            tmp.next = dummy.next
            dummy.next = tmp
        head2 = dummy.next
        
        # merge two linked lists head1 and head2
        p1, p2 = head1, head2
        while p2:
            tmp1, tmp2 = p1.next, p2.next
            p1.next, p2.next = p2, tmp1
            p1, p2 = tmp1, tmp2
            