# Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

# For example:
# Given the below binary tree and sum = 22,
#               5
#              / \
#             4   8
#            /   / \
#           11  13  4
#          /  \    / \
#         7    2  5   1
# return
# [
#    [5,4,11,2],
#    [5,8,4,5]
# ]

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @param sum, an integer
    # @return a list of lists of integers
    def pathSum_1_dfs(self, root, sum):

        def dfs(root, curr_sum, valuelist):
            if root.left == None and root.right == None:
                if curr_sum == sum: res.append(valuelist)
            if root.left:
                dfs(root.left, curr_sum + root.left.val, valuelist + [root.left.val])
            if root.right:
                dfs(root.right, curr_sum + root.right.val, valuelist + [root.right.val])

        res = []
        if root == None: return []
        dfs(root, root.val, [root.val])
        return res