# Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param two ListNodes
    # @return a ListNode
    def mergeTwoLists(self, l1, l2):
        head = ListNode(0)
        curr = head
        i, j = l1, l2
        while i or j:
            if i == None:
                curr.next, j = j, j.next
            elif j == None:
                curr.next, i = i, i.next
            elif i.val > j.val:
                curr.next, j = j, j.next
            else:
                curr.next, i = i, i.next
            curr = curr.next
        return head.next