"""
2.1 Implement an algorithm to find the kth to last element of a singly linked
 list.
"""
from linkedlist import Node, LinkedList


def kth_tolast(head, k):
    # Time complexity: O(n) - iterate linkedlist once
    # Space complexity: O(1) - only need two pointers
    if k < 0:
        return
    fast = head
    slow = head
    while fast:
        fast = fast.next
        if k > 0:
            k -= 1
        else:
            slow = slow.next
    return slow


def main():
    ll = LinkedList()
    data = [char for char in '123456789']
    for char in data[::-1]:
        node = Node(char)
        ll.insert(node)
    print ll
    print ll.size()
    print kth_tolast(ll.head, 5).val == '123456789'[-5]


if __name__ == "__main__":
    main()
