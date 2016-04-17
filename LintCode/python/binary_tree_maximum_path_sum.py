"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""
class Solution:
    """
    @param root: The root of binary tree.
    @return: An integer
    """
    def maxPathSum(self, root):
        # [divide and conquer]
        # time:  O(n)
        # space: O(1)
        if not root:
            return 0

        _, maxPath = self.helper(root)

        return maxPath

    def helper(self, root):
        if not root:
            return (0, -sys.maxint)

        # Divide
        leftSinglePath, leftMaxPath = self.helper(root.left)
        rightSinglePath, rightMaxPath = self.helper(root.right)

        # Conquer
        singlePath = max(leftSinglePath, rightSinglePath) + root.val
        singlePath = max(singlePath, 0)

        maxPath = max(leftMaxPath, rightMaxPath, leftSinglePath + root.val + rightSinglePath)

        return (singlePath, maxPath)

