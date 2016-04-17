# Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

# You should preserve the original relative order of the nodes in each of the two partitions.

# For example,
# Given 1->4->3->2->5->2 and x = 3,
# return 1->2->2->4->3->5.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param x, an integer
    # @return a ListNode
    def partition_1(self, head, x):
        if head == None: return
        head_less = curr_less = ListNode(0)
        head_greater = curr_greater = ListNode(0)
        curr = head
        while curr:
            prev, curr = curr, curr.next
            if prev.val < x:
                curr_less.next = prev
                curr_less = prev
                curr_less.next = None
            else:
                curr_greater.next = prev
                curr_greater = prev
                curr_greater.next = None
        curr_less.next = head_greater.next
        return head_less.next