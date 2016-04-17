"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""
import copy
class Solution:
    """
    @param root: The root of the binary search tree.
    @param A and B: two nodes in a Binary.
    @return: Return the least common ancestor(LCA) of the two nodes.
    """
    def lowestCommonAncestor(self, root, A, B):
        # [divide and conquer]
        # time:  O(n)
        # space: O(1)
        if not root or root == A or root == B:
            return root

        # Divide
        left = self.lowestCommonAncestor(root.left , A, B)
        right = self.lowestCommonAncestor(root.right , A, B)

        # Conquer
        if left and right:
            return root
        elif left:
            return left
        elif right:
            return right
        else:
            return None

