# A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

# Return a deep copy of the list.

# Hide Tags [Hash Table] [Linked List]

# Definition for singly-linked list with a random pointer.
# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

class Solution:
    # @param head, a RandomListNode
    # @return a RandomListNode
    def copyRandomList_1(self, head):
        if head == None: return
        # insert new node with same label value after each node
        tmp = head
        while tmp:
            newNode = RandomListNode(tmp.label)
            newNode.next, tmp.next = tmp.next, newNode
            tmp = tmp.next.next
        # copy random value
        tmp = head
        while tmp:
            if tmp.random:
                tmp.next.random = tmp.random.next
            tmp = tmp.next.next
        # seperate old and new linked list
        newhead = head.next
        pold, pnew = head, newhead
        while pnew.next:
            pold.next, pold = pnew.next, pold.next
            pnew.next, pnew = pold.next, pnew.next
        pold.next, pnew.next = None, None
        return newhead


    def copyRandomList_2(self, head):
        # Store mapping between old and new nodes
        mapOldToNew = {None:None}
        # Clone all old nodes with same label values
        curr = head
        while curr:
            mapOldToNew[curr] = RandomListNode(curr.label)
            curr = curr.next
        # Assign next and random value of cloned nodes
        curr = head
        while curr:
            mapOldToNew[curr].next = mapOldToNew[curr.next]
            mapOldToNew[curr].random = mapOldToNew[curr.random]
            curr = curr.next
        return mapOldToNew[head]