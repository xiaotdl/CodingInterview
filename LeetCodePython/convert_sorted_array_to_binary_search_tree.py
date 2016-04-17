# Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param num, a list of integers
    # @return a tree node
    def sortedArrayToBST_1(self, num):
        if num == None: return
        return self.recursive_worker(num, 0, len(num) - 1)
    
    def recursive_worker(self, arr, start, end):
        if start > end: return
        mid = (start + end) / 2
        node = TreeNode(arr[mid])
        node.left = self.recursive_worker(arr, start, mid - 1)
        node.right = self.recursive_worker(arr, mid + 1, end)
        return node
