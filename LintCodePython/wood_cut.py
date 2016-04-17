class Solution:
    """
    @param L: Given n pieces of wood with length L[i]
    @param k: An integer
    return: The max_pieceimum length of the small pieces.
    """
    def woodCut1(self, L, k):
        # intuitive
        # time:  O(n * sum(L)/k)
        # space: O(1)
        Sum = sum(L)
        if not L or not k or Sum < k:
            return 0

        pieces = 0
        max_piece = Sum / k
        while True:
            pieces = sum(l / max_piece for l in L)
            if pieces >= k:
                break
            max_piece -= 1
        return max_piece

    def woodCut2(self, L, k):
        # binary seach
        # time:  O(n * log(max(L)))
        # space: O(1)
        Sum = sum(L)
        if not L or not k or Sum < k:
            return 0

        start, end = 1, max(L)
        while start + 1 < end:
            mid = start + (end - start) / 2
            pieces = sum(l / mid for l in L)
            if pieces >= k:
                start = mid
            else:
                end = mid

        if sum(l / end for l in L) >= k:
            return end
        else:
            return start



if __name__ == '__main__':
    print Solution().woodCut1([232, 124, 456], 7)
    print Solution().woodCut1([2147483644,2147483645,2147483646,2147483647], 4)

    print Solution().woodCut2([232, 124, 456], 7)
    print Solution().woodCut2([2147483644,2147483645,2147483646,2147483647], 4)
# >>>
# 114
# 2147483644
#
# 114
# 2147483644
