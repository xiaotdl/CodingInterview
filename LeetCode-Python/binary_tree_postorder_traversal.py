# Given a binary tree, return the postorder traversal of its nodes' values.

# For example:
# Given binary tree {1,#,2,3},
#    1
#     \
#      2
#     /
#    3
# return [3,2,1].

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def postorderTraversal_1_recursive(self, root):
        res = []
        self.postorder_traversal_recursive(root, res)
        return res

    def postorder_traversal_recursive(self, root, res):
        if root == None: return
        self.postorder_traversal_recursive(root.left, res)
        self.postorder_traversal_recursive(root.right, res)
        res.append(root.val)
        return res


    def postorderTraversal_2_iterative(self, root):
        if root == None: return []
        res = []
        stack = []
        last_node_visited = None
        while root or stack:
            if root:
                stack.append(root)
                root = root.left
            else:
                peek_node = stack[-1]
                if peek_node.right and last_node_visited != peek_node.right:
                    root = peek_node.right
                else:
                    stack.pop()
                    res.append(peek_node.val)
                    last_node_visited = peek_node
        return res