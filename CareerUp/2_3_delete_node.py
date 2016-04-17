"""
2.3 Implement an algorithm to delete a node in the middle of a singly linked
 list, given only access to that node.
EXAMPLE
Input: the node c from the linked list a->b->c->d->e
Result: nothing is returned, but the new linked list looks like a- >b- >d->e
"""
from linkedlist import Node, LinkedList


def delete_node(node):
    # Time complexity: O(1)
    # Space complexity: O(1)
    if node == None or node.next == None:  # @IgnorePep8
        return

    next = node.next  # @ReservedAssignment
    node.val = next.val
    node.next = next.next


def main():
    tobedeleted = None
    ll = LinkedList()
    data = [char for char in '123456789']
    for char in data[::-1]:
        node = Node(char)
        ll.insert(node)
        if int(char) == 5:
            tobedeleted = node
    print ll
    delete_node(tobedeleted)
    print ll, type(ll)
    print list('12346789'), type(list('12346789'))
    print "%s" % ll == "%s" % list('12346789')


if __name__ == "__main__":
    main()
