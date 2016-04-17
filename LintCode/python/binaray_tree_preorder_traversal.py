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
    @return: Preorder in ArrayList which contains node values.
    """
    def preorderTraversal1(self, root):
        # [recursive]
        # time:  O(n)
        # space: O(1)
        if not root:
            return []

        res = []
        self.helper(root, res)
        return res

    def helper(self, root, result):
        if not root:
            return

        result.append(root.val)
        self.helper(root.left, result)
        self.helper(root.right, result)

    def preorderTraversal2(self, root):
        # [iterative], [stack]
        # time:  O(n)
        # space: O(n)
        if not root:
            return []

        res = []
        stack = []
        stack.append(root)
        while stack:
            node = stack.pop()
            res.append(node.val)
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)

        return res

