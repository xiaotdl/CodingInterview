class Solution:
    """
    @param triangle: a list of lists of integers.
    @return: An integer, minimum path sum.
    """
    min_sum = sys.maxint
    def minimumTotal(self, triangle):
        # [dfs]
        # time:  O(n?)
        # space: O(1?)
        if not triangle or not triangle[0]:
            return 0
        self.traverse(triangle, 0, 0, 0)
        return self.min_sum

    def traverse(self, triangle, row, col, curr_sum):
        if row == len(triangle):
            self.min_sum = min(self.min_sum, curr_sum)
            return

        self.traverse(triangle, row + 1, col, curr_sum + triangle[row][col])
        self.traverse(triangle, row + 1, col + 1, curr_sum + triangle[row][col])

    def minimumTotal(self, triangle):
        # [divide and conquer]
        # time:  O(n?)
        # space: O(1?)
        if not triangle:
            return 0
        return self.helper(triangle, 0, 0)

    def helper(self, triangle, row, col):
        if row > len(triangle) - 1 or col > len(triangle[row]) - 1:
            return 0

        # Divide
        leftSum = self.helper(triangle, row + 1, col)
        rightSum = self.helper(triangle, row + 1, col + 1)

        # Conquer
        return min(leftSum, rightSum) + triangle[row][col]

