# Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

# For example,
# Given n = 3, your program should return all 5 unique BST's shown below.

#    1         3     3      2      1
#     \       /     /      / \      \
#      3     2     1      1   3      2
#     /     /       \                 \
#    2     1         2                 3
# confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @return a list of tree node
    def generateTrees_1_dfs(self, n):
        return self.dfs(1, n)
    
    def dfs(self, start, end):
        if start > end: return [None]
        res = []
        for rootval in xrange(start, end + 1):
            left_tree = self.dfs(start, rootval - 1)
            right_tree = self.dfs(rootval + 1, end)
            for i in left_tree:
                for j in right_tree:
                    root = TreeNode(rootval)
                    root.left = i
                    root.right = j
                    res.append(root)
        return res