# Given a sorted linked list, delete all duplicates such that each element appear only once.

# For example,
# Given 1->1->2, return 1->2.
# Given 1->1->2->3->3, return 1->2->3.

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
        curr = head
        while curr.next:
            if curr.val == curr.next.val:
                curr.next = curr.next.next
            else:
                curr = curr.next
        return head


    def deleteDuplicates_2(self, head):
        if head == None: return
        prev, curr = head, head.next
        while curr:
            if curr.val == prev.val:
                prev.next = curr.next
            else:
                prev = curr
            curr = curr.next
        return head


    def deleteDuplicates_3_for_unsorted_linked_list(self, head):
        list = []
        curr = head
        while curr:
            if curr.val not in list:
                list.append(curr.val)
                prev = curr
            else:
                prev.next = curr.next
            curr = curr.next
        return head


