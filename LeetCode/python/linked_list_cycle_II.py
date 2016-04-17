# Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

# Follow up:
# Can you solve it without using extra space?

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a list node
    def detectCycle_1(self, head):
        # when slow pointer and fast pointer collides in the circle
        # the collide node and head are both k nodes away from circle start node
        if head == None:
            return
        slow_runner = head
        fast_runner = head
        while fast_runner and fast_runner.next:
            slow_runner = slow_runner.next
            fast_runner = fast_runner.next.next
            if slow_runner == fast_runner:
                break
        if fast_runner == None or fast_runner.next == None:
            return
        slow_runner = head
        while slow_runner != fast_runner:
            slow_runner = slow_runner.next
            fast_runner = fast_runner.next
        return slow_runner