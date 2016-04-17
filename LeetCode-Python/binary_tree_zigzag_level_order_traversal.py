# Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

# For example:
# Given binary tree {3,9,20,#,#,15,7},
#     3
#    / \
#   9  20
#     /  \
#    15   7
# return its zigzag level order traversal as:
# [
#   [3],
#   [20,9],
#   [15,7]
# ]
# confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of lists of integers
    def zigzagLevelOrder_1_bfs(self, root):
        if root == None: return []
        res = []
        curr_level = [root]
        res.append([root.val])
        # set a flag to record when to reverse
        # flag=False: normal; flag=True: reverse
        flag = True
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
            if flag == True:
                res.append(res_next_level[::-1])
            else:
                res.append(res_next_level)
            flag = not flag
            curr_level = next_level
        return res