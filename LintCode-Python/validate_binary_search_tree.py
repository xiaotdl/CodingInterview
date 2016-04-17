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
    @return: True if the binary tree is BST, or false
    """
    def isValidBST1(self, root):
        # [divide and conquer]
        # time:  O(n)
        # space: O(1)
        if not root:
            return True

        isBST, _, __ = self.helper(root)
        return isBST

    def helper(self, root):
        if not root:
            return (True, sys.maxint, -sys.maxint)

        # Divide
        isLeftBST, leftMin, leftMax = self.helper(root.left)
        isRightBST, rightMin, rightMax = self.helper(root.right)

        # Conquer
        return (isLeftBST and isRightBST and leftMax < root.val and root.val < rightMin,
                min(leftMin, root.val),
                max(rightMax, root.val))

    prevNode = None
    def isValidBST2(self, root):
        # [dfs]
        # time:  O(n)
        # space: O(1)
        # inorder traversal is ascending
        if not root:
            return True

        if not self.isValidBST(root.left):
            return False

        if self.prevNode and self.prevNode.val >= root.val:
            return False
        self.prevNode = root

        if not self.isValidBST(root.right):
            return False

        return True

