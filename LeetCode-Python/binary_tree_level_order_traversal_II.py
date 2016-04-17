# Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

# For example:
# Given binary tree {3,9,20,#,#,15,7},
#     3
#    / \
#   9  20
#     /  \
#    15   7
# return its bottom-up level order traversal as:
# [
#   [15,7],
#   [9,20],
#   [3]
# ]

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of lists of integers
    def levelOrderBottom_1_bfs(self, root):
        # bfs, mimic queue with two lists
        if root == None: return []
        res = []
        curr_level = [root]
        res.append([root.val])
        while True:
            next_level = []
            res_next_level = []
            for node in curr_level:
                for child in (node.left, node.right):
                    if child:
                        next_level.append(child)
                        res_next_level.append(child.val)
            if next_level == []:
                break
            res.append(res_next_level)
            curr_level = next_level
        return res[::-1]