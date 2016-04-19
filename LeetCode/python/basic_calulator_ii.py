class Solution(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        """
        # time: O(n^2)
        # space: O(n)
        nums = []
        ops = []

        num = None
        for char in s:
            if char == ' ':
                if num is not None:
                    nums.append(num)
                    num = None
                continue
            elif char in ['+', '-', '*', '/']:
                ops.append(char)
                if num is not None:
                    nums.append(num)
                    num = None
            else:
                num = 10 * num + int(char) if num is not None else int(char)
        if num is not None:
            nums.append(num)

        if len(nums) == 1:
            return nums[0]

        while ops:
            if '*' in ops:
                i = ops.index('*')
                nums[i] = nums[i] * nums[i + 1]
                ops.pop(i)
                nums.pop(i + 1)
            elif '/' in ops:
                i = ops.index('/')
                nums[i] = nums[i] / nums[i + 1]
                ops.pop(i)
                nums.pop(i + 1)
            elif '+' in ops:
                i = ops.index('+')
                nums[i] = nums[i] + nums[i + 1]
                ops.pop(i)
                nums.pop(i + 1)
            elif '-' in ops:
                i = ops.index('-')
                nums[i] = nums[i] - nums[i + 1]
                ops.pop(i)
                nums.pop(i + 1)

        return nums[0]


from collections import deque
class Solution2(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        """
        # time: O(n)
        # space: O(n)
        nums = deque() # deque
        ops = deque()  # queue

        def add_into(num, nums, ops):
            if num is not None:
                nums.append(num)
                if ops:
                    if ops[-1] == '*':
                        rhs = nums.pop()
                        lhs = nums.pop()
                        nums.append(lhs * rhs)
                    elif ops[-1] == '/':
                        rhs = nums.pop()
                        lhs = nums.pop()
                        nums.append(lhs / rhs)
        num = None
        for char in s:
            if char == ' ':
                add_into(num, nums, ops)
                num = None
                continue
            elif char in ['+', '-', '*', '/']:
                add_into(num, nums, ops)
                num = None
                ops.append(char)
            else:
                num = 10 * num + int(char) if num is not None else int(char)
        add_into(num, nums, ops)
        num = None

        while ops:
            op = ops.popleft()
            if op == '+':
                lhs = nums.popleft()
                rhs = nums.popleft()
                nums.appendleft(lhs + rhs)
            elif op == '-':
                lhs = nums.popleft()
                rhs = nums.popleft()
                nums.appendleft(lhs - rhs)

        return nums[0]
