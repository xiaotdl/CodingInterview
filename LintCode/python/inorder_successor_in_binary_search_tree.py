"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""

class Solution(object):
    """
    @param root <TreeNode>: The root of the BST.
    @param p <TreeNode>: You need find the successor node of p.
    @return <TreeNode>: Successor of p.
    """
    found = False
    def inorderSuccessor1(self, root, p):
        # [divide and conquer]
        # time:  O(n)
        # space: O(1)
        if not root:
            return None

        # Divide
        left = self.inorderSuccessor(root.left, p)
        if self.found:
            self.found = False
            return root
        if root == p:
            self.found = True
        right = self.inorderSuccessor(root.right, p)

        # Conquer
        if left:
            return left
        elif right:
            return right
        else:
            return None

    def inorderSuccessor2(self, root, p):
        # [inorder traversal]
        # time:  O(h), where h is the height of the BST.
        # space: O(1)
        successor = None
        while root and root != p:
            if root.val > p.val:
                successor = root
                root = root.left
            else:
                root = root.right

        # p not found
        if not root:
            return None

        if root.right:
            # find next largest on right subtree
            root = root.right
            while root.left:
                root = root.left
            return root
        else:
            # next largest is last turning left node
            return successor

