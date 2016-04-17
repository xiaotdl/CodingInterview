class Queue:
    """FIFO"""
    def __init__(self):
        self.items = []

    def add(self, item):
        self.items.append(item)

    def delete(self):
        item_to_delete = self.items[0]
        del self.items[0]
        return item_to_delete

    def peek(self):
        return self.items[0]

    def size(self):
        return len(self.items)

    def report(self):
        return self.items

def test_queue():
    q = Queue()
    q.add(1)
    q.add(2)
    q.add(3)
    print q.report()
    print q.delete()
    print q.report()
    print q.peek()
    print q.size()

if __name__ == "__main__":
    test_queue()

# # Python implementation
# # remove <-- [....] <-- insert
# # Queue with insertion from right and removal from left

# from collections import deque
 
# q = deque()
# # deque([])
 
# q.append( 1 )
# # deque([ 1 ])
 
# q.append( 2 )
# # deque([ 1, 2 ])
 
# q.popleft()
# # 1