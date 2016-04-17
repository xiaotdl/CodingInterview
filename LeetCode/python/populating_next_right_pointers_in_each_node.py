# Given a binary tree

#     struct TreeLinkNode {
#       TreeLinkNode *left;
#       TreeLinkNode *right;
#       TreeLinkNode *next;
#     }
# Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

# Initially, all next pointers are set to NULL.

# Note:

# You may only use constant extra space.
# You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
# For example,
# Given the following perfect binary tree,
#          1
#        /  \
#       2    3
#      / \  / \
#     4  5  6  7
# After calling your function, the tree should look like:
#          1 -> NULL
#        /  \
#       2 -> 3 -> NULL
#      / \  / \
#     4->5->6->7 -> NULL

class Solution:
    # @param root, a tree node
    # @return nothing
    def connect_1(self, root):
        # traverse through the binary tree by level using queue 
        # assign None to node number 1, 3, 7, 15, 31 ... condition: N & (N+1) == 0
        if root == None: return
        queue = collections.deque()
        queue.append(root)
        visited_num = 0
        while queue:
            curr = queue.popleft()
            visited_num += 1
            if curr.left and curr.right:
                queue.append(curr.left)
                queue.append(curr.right)
            curr.next = None if visited_num & (visited_num + 1) == 0 else queue[0]