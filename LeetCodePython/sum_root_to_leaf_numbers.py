# Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

# An example is the root-to-leaf path 1->2->3 which represents the number 123.

# Find the total sum of all root-to-leaf numbers.

# For example,

#     1
#    / \
#   2   3
# The root-to-leaf path 1->2 represents the number 12.
# The root-to-leaf path 1->3 represents the number 13.

# Return the sum = 12 + 13 = 25.

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def sumNumbers_1_recursive(self, root):
        return self.recursive_worker(root, 0)

    def recursive_worker(self, root, tmp_val):
        if root == None: 
            return 0
        tmp_val = tmp_val*10 + root.val
        if root.left == None and root.right == None: 
            return tmp_val
        return self.recursive_worker(root.left, tmp_val) +\
               self.recursive_worker(root.right, tmp_val)