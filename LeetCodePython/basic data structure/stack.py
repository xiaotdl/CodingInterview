class Stack:
    """LIFO"""
    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        item_to_pop = self.items[-1]
        del self.items[-1]
        return item_to_pop

    def peek(self):
        return self.items[-1]

    def size(self):
        return len(self.items)

    def report(self):
        return self.items

    def isEmpty(self):
        if self.items == []:
            return True
        else:
            return False

def test_stack():
    stack = Stack()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    print stack.report()
    print stack.pop()
    print stack.report()
    print stack.peek()
    print stack.size()
    print stack.isEmpty()


if __name__ == "__main__":
    test_stack()


# # Python implementation
# # [....] <-- insert
# # [....] --> remove
# # Stack with both insertion and removal from right

# --1--
# from collections import deque
 
# stack = deque()
# # deque([])
 
# stack.append( 1 )
# # deque([ 1 ])
 
# stack.append( 2 )
# # deque([ 1, 2 ])
 
# stack.pop()
# # 2

# --2--
# stack = list()

# stack.append( 1 )
# # [ 1 ]

# stack.append( 2 )
# # [ 1, 2 ]

# stack.pop()
# # 2
