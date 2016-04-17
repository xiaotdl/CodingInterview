# You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

# Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
# Output: 7 -> 0 -> 8

# Hide Tags [Linked List] [Math]


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def addTwoNumbers(self, l1, l2):
        if l1 == None: return l2
        if l2 == None: return l1
        
        flag = 0
        dummy = ListNode(0)
        p, p1, p2 = dummy, l1, l2
        while p1 and p2:
            p.next = ListNode((p1.val + p2.val + flag) % 10)
            flag = (p1.val + p2.val + flag) / 10
            p, p1, p2 = p.next, p1.next, p2.next
            
        for left in (p1, p2):
            if left:
                while left:
                    p.next = ListNode((left.val + flag) % 10)
                    flag = (left.val + flag) / 10
                    p, left = p.next, left.next
        
        if flag == 1: p.next = ListNode(1)
        
        return dummy.next
                