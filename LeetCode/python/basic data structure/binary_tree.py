class Node():
    """Tree Node"""
    def __init__(self, val = None):
        self.left = None
        self.right = None
        self.val = val

#     1
#    / \
#   2   3
#  / \ / \
# 4  5 6  7
def create_binary_tree():
    n1 = Node(1)
    n2 = Node(2)
    n3 = Node(3)
    n4 = Node(4)
    n5 = Node(5)
    n6 = Node(6)
    n7 = Node(7)
    n1.left, n1.right = n2, n3
    n2.left, n2.right = n4, n5
    n3.left, n3.right = n6, n7
    return n1

"""
<--(Binary) Tree Traversal Summary-->
<Types> DFS, BFS
    <Assumption> left->right, left nodes always have priority over right nodes
    <DFS> Depth-first search, depth-first order
        <Implementation> recursive, iterative->stack
        <Pre-order>  root->left->right, visit root prior to left&right node
        <In-order>   left->root->right, visit root between left&right node
        <Post-order> left->right->root, visit root after left&right node
        <Morris in-order traversal using threading> 
          (http://en.wikipedia.org/wiki/Tree_traversal#Morris_in-order_traversal_using_threading)
    <BFS> Breadth-first search, level order
        <Implementation> corecursive, iterative-> queue
<Applications>
    <Pre-order>  Create a copy of BST; Get prefix expression on of an expression tree
                                       (http://en.wikipedia.org/wiki/Polish_notation)
    <In-order>   Get nodes of BST in non-decreasing order
    <Post-order> Delete nodes of BST; Get the postfix expression of an expression tree
                                       (http://en.wikipedia.org/wiki/Reverse_Polish_notation)         
<Expected results>
    <DFS>        [1, 2, 4, 5, 3, 6, 7]
    <Pre-order>  [1, 2, 4, 5, 3, 6, 7]
    <In-order>   [4, 2, 5, 1, 6, 3, 7]
    <Post-order> [4, 5, 2, 6, 7, 3, 1]
    <BFS>        [1, 2, 3, 4, 5, 6, 7]
"""
def dfs(root, res):
    """depth first search: recursive solution"""
    if root == None: return
    res.append(root.val)
    dfs(root.left, res)
    dfs(root.right, res)
    return res

def preorder_traversal_recursive(root, res):
    if root == None: return
    res.append(root.val)
    preorder_traversal_recursive(root.left, res)
    preorder_traversal_recursive(root.right, res)
    return res

def inorder_traversal_recursive(root, res):
    if root == None: return
    inorder_traversal_recursive(root.left, res)
    res.append(root.val)
    inorder_traversal_recursive(root.right, res)
    return res

def postorder_traversal_recursive(root, res):
    if root == None: return
    postorder_traversal_recursive(root.left, res)
    postorder_traversal_recursive(root.right, res)
    res.append(root.val)
    return res

def preorder_traversal_iterative(root):
    if root == None: return []
    res = []
    stack = []
    stack.append(None)
    while root:
        res.append(root.val)
        if root.right: stack.append(root.right)
        if root.left: stack.append(root.left)
        root = stack.pop()
    return res

def inorder_traversal_iterative(root):
    if root == None: return []
    res = []
    stack = []
    while root or stack:
        if root:
            stack.append(root)
            root = root.left
        else:
            root = stack.pop()
            res.append(root.val)
            root = root.right
    return res

def postorder_traversal_iterative(root):
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

from collections import deque
def bfs(root):
    """breadth first search: iterative solution using a queue"""
    if root == None: return []
    res = []
    q = deque()
    q.append(root)
    while q:
        node = q.popleft()
        res.append(node.val)
        for node in (node.left, node.right):
            if node:
                q.append(node)
    return res

def test_binary_tree():
    root = create_binary_tree()
    print dfs(root, []), "<dfs>"
    print preorder_traversal_recursive(root, []), "<preorder-recursive>"
    print preorder_traversal_iterative(root), "<preorder-iterative>"
    print inorder_traversal_recursive(root, []), "<inorder-recursive>"
    print inorder_traversal_iterative(root), "<inorder-iterative>"
    print postorder_traversal_recursive(root, []), "<postorder-recursive>"
    print postorder_traversal_iterative(root), "<postorder-iterative>"
    print bfs(root), "<bfs>"

if __name__ == "__main__":
    test_binary_tree()
