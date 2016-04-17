"""
2.1 Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
"""
from linkedlist import Node, LinkedList


def remove_duplicates(head):
    # Time complexity: O(n) - linkedlist is iterated once
    # Space complexity: O(n) - created a hashset to store linkedlist element
    if not head or not head.next:
        return

    st = set()
    prev = head
    curr = head.next
    st.add(prev.val)
    while curr:
        if curr.val not in st:
            st.add(curr.val)
            prev = prev.next
            curr = curr.next
        else:
            prev.next = curr.next
            curr = prev.next
    return head


def main():
    ll = LinkedList()
    data = [char for char in 'FOLLOW UP']
    for char in data[::-1]:
        node = Node(char)
        ll.insert(node)
    remove_duplicates(ll.head)
    print "%s" % ll == "%s" % ['F', 'O', 'L', 'W', ' ', 'U', 'P']


if __name__ == "__main__":
    main()
