# Given a binary tree, determine if it is height-balanced.

# For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a boolean
    def isBalanced_1(self, root):
        if root == None:
            return True
        if abs(self.getHeight(root.left) - self.getHeight(root.right)) <= 1:
            return self.isBalanced(root.left) and self.isBalanced(root.right)
        return False
    
    def getHeight(self, root):
        if root == None:
            return 0
        return max(self.getHeight(root.left), self.getHeight(root.right)) + 1