# Definition for singly-linked list.
class Node(object):
    def __init__(self, val):
        self.val = val
        self.next = None


class LinkedList(object):
    def __init__(self, head=None):
        self.head = head

    def insert(self, node):
        if not self.head:
            self.head = node
        else:
            node.next = self.head
            self.head = node

    def size(self):
        size = 0
        curr = self.head
        while curr:
            size += 1
            curr = curr.next
        return size

    def __str__(self):
        ll = []
        curr = self.head
        while curr:
            ll.append(curr.val)
            curr = curr.next
        return "%s" % ll
