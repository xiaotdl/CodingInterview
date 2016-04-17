# Given a binary tree, return the preorder traversal of its nodes' values.

# For example:
# Given binary tree {1,#,2,3},
#    1
#     \
#      2
#     /
#    3
# return [1,2,3].

# Note: Recursive solution is trivial, could you do it iteratively?

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def preorderTraversal_1_recursive(self, root):
        res = []
        self.preorder_traversal_recursive(root, res)
        return res
    
    def preorder_traversal_recursive(self, root, res):
        if root == None: return
        res.append(root.val)
        self.preorder_traversal_recursive(root.left, res)
        self.preorder_traversal_recursive(root.right, res)
        return res


    def preorderTraversal_2_iterative(self, root):
        result = []
        stack = []
        while root or stack:
            if root:
                stack.append(root)
                result.append(root.val)
                root = root.left
            else:
                root = stack.pop()
                root = root.right
        return result