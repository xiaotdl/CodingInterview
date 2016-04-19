class Solution:
    # @param n: an integer
    # @return an integer f(n)
    memory = dict()
    def fibonacci(self, n):
        # time: O(n)
        # space: O(n)
        # tag: recursion
        if n == 1:
            return 0
        elif n == 2:
            return 1
        else:
            if n - 1 not in self.memory:
                self.memory[n - 1] = self.fibonacci(n - 1)
            if n - 2 not in self.memory:
                self.memory[n - 2] = self.fibonacci(n - 2)
            return self.memory[n - 1] + self.memory[n - 2]

class Solution2:
    # @param n: an integer
    # @return an integer f(n)
    def fibonacci(self, n):
        # time: O(n)
        # space: O(1)
        # tag: non-recursion, dp
        a = 0
        b = 1
        if n == 1:
            return a
        elif n == 2:
            return b

        for i in range(3, n + 1):
            c = a + b
            a = b
            b = c

        return c
