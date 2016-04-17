class Solution:
    """
    @param S: A set of numbers.
    @return: A list of lists. All valid subsets.
    """
    def subsetsWithDup(self, S):
        # recursion
        # time:  O(2^n)
        # space: O(n)
        if not S:
            return []

        result = []
        self.helper(sorted(S), 0, [], result)
        return result

    def helper(self, S, pos, curr_list, result):
        result.append(curr_list[:])

        for i in range(pos, len(S)):
            # cut the leafs when there will be a dup node on same level
            if i != pos and S[i] == S[i - 1]:
                continue

            curr_list.append(S[i])
            self.helper(S, i + 1, curr_list, result)
            curr_list.pop()


if __name__ == '__main__':
    print Solution().subsetsWithDup([1, 1, 1])
# >>>
# [[], [1], [1, 1], [1, 1, 1]]


