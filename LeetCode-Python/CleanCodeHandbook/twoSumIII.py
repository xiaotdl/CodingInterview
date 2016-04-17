class TwoSum1(object):
    # [hash table]
    # add - O(1) runtime
    # find - O(n) runtime
    # O(n) space

    d = dict() # {num1: count1, num2: count2, ...}

    def add(self, num):
        self.d[num] = self.d.get(num, 0) + 1

    def find(self, target):
        for num in self.d:
            if target - num == num:
                if self.d[num] >= 2:
                    return True
            elif target - num in self.d:
                return True
        return False

class TwoSum2(object):
    # [hash table]
    # add - O(n) runtime
    # find - O(1) runtime
    # O(n^2) space

    l = list() # [num1, num2, ...]
    d = dict() # {sum1: [(num1, num3), (num2, num5)...]}

    def add(self, num):
        for curr_num in self.l:
            if curr_num + num not in self.d:
                self.d[curr_num + num] = []
            self.d[curr_num + num].append([curr_num, num])
        self.l.append(num)

    def find(self, target):
        return target in self.d

class TwoSum3(object):
    pass
    # [binary search] maintain a sorted array
    # add - O(logn) runtime
    # find - O(n) runtime
    # O(n) space


for solution in (TwoSum1(), TwoSum2()):
    solution.add(1);
    solution.add(3);
    solution.add(5);
    print solution.find(4);
    print solution.find(7);
# >>>
# True
# False
