class Solution:
    """
    @param S: The set of numbers.
    @return: A list of lists. See example.
    """
    def subsets(self, S):
        # recursion
        # time:  O(2^n)
        # space: O(n)
        if not S: # if S is not None and S != ''/[]/{}
            return []

        result = []
        self.helper(sorted(S), 0, [], result)
        return result

    def helper(self, S, pos, curr_list, result):
        result.append(curr_list[:])

        for i in range(pos, len(S)):
            curr_list.append(S[i])
            self.helper(S, i + 1, curr_list, result)
            curr_list.pop()


if __name__ == '__main__':
    print Solution().subsets([1,2,3])
# >>>
# [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]

