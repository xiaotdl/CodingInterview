# Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

# Hide Tags [Depth-first Search] [Linked List]


# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a list node
    # @return a tree node
    def sortedListToBST(self, head):
        arr = []
        p = head
        while p:
            arr.append(p.val)
            p = p.next
        return self.sortedArrayToBST(arr)
    
    def sortedArrayToBST(self, arr):
        # recursive solution
        length = len(arr)
        if length == 0:
            return
        if length == 1:
            return TreeNode(arr[0])
        root = TreeNode(arr[length/2])
        root.left  = self.sortedArrayToBST(arr[:length/2])
        root.right = self.sortedArrayToBST(arr[length/2 + 1:])
        return root
        