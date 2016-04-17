"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None

Example of iterate a tree:
iterator = BSTIterator(root)
while iterator.hasNext():
    node = iterator.next()
    do something for node
"""
class BSTIterator:
    # [stack]
    # time:  O(h), h is the height of the BST.
    # space: O(1)
    #@param root: The root of binary tree.
    def __init__(self, root):
        self.curr = root
        self.stack = []

    #@return: True if there has next node, or false
    def hasNext(self):
        return self.curr or self.stack

    #@return: return next node
    def next(self):
        while self.curr:
            self.stack.append(self.curr)
            self.curr = self.curr.left
        next = self.stack.pop()
        self.curr = next.right
        return next
