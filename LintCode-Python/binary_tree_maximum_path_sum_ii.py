"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""
class Solution:
    """
    @param root the root of binary tree.
    @return an integer
    """
    def maxPathSum2(self, root):
        # [divide and conquer]
        # time:  O(n)
        # space: O(1)
        if not root:
            return 0

        # Divide
        leftSinglePath = self.maxPathSum2(root.left)
        rightSinglePath = self.maxPathSum2(root.right)

        # Conquer
        return max(leftSinglePath, rightSinglePath, 0) + root.val
