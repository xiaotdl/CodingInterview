# Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

# For example,
# Given 1->2->3->3->4->4->5, return 1->2->5.
# Given 1->1->1->2->3, return 2->3.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def deleteDuplicates_1(self, head):
        if head == None: return
        helper = ListNode(0)
        helper.next = head
        prev, curr = helper, head
        while curr:
            while curr.next and curr.next.val == prev.next.val:
                curr = curr.next
            if prev.next == curr:
                prev = curr
            else:
                prev.next = curr.next
            curr = curr.next
        return helper.next