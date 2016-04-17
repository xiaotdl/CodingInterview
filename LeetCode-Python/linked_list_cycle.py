# Given a linked list, determine if it has a cycle in it.

# Follow up:
# Can you solve it without using extra space?

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a boolean
    def hasCycle_1(self, head):
        if head == None: 
            return False
        slow_runner = head
        fast_runner = head
        while fast_runner and fast_runner.next:
            slow_runner = slow_runner.next
            fast_runner = fast_runner.next.next
            if slow_runner == fast_runner:
                return True
        return False