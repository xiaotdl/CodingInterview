"""
2.4 Write code to partition a linked list around a value x, such that all nodes
 less than x come before, all nodes greater than or equal to x.
"""
from linkedlist import Node, LinkedList


def partition_linkedlist(node):
    # Time complexity: O(1)
    # Space complexity: O(1)
    


def main():
    selected = None
    ll = LinkedList()
    data = [char for char in '3728511296']
    for char in data[::-1]:
        node = Node(char)
        ll.insert(node)
        if int(char) == 5:
            selected = node
    print ll
    partition_linkedlist(selected)
    print ll, type(ll)
    print "%s" % ll == "%s" % list('12346789')


if __name__ == "__main__":
    main()
