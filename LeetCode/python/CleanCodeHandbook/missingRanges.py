class Solution(object):
    def findMissingRanges(self, vals, start, end):
        """
        :type vals: list
        :type start: int
        :type end: int
        :rtype: list
        """
        # [one pass]
        # O(n) runtime, O(1) space
        # insert dummy head and tail
        if vals is None or start is None or end is None or start > end:
            return []

        ranges = []
        head = start - 1
        tail = end + 1
        prev = head
        for i in range(len(vals) + 1):
            curr = tail if i == len(vals) else vals[i]
            if curr - prev > 1:
                ranges.append(self.getRange(prev + 1, curr - 1))
            prev = curr
        return ranges

    def getRange(self, left, right):
        return str(left) if left == right else "%s -> %s" % (left, right)



print Solution().findMissingRanges([], 0, 99)   # ["0->99"]
print Solution().findMissingRanges([0], 0, 99)  # ["1->99"]
print Solution().findMissingRanges([99], 0, 99) # ["0->98"]
print Solution().findMissingRanges([0, 1, 3, 50, 75], 0, 99) # ['2', '4 -> 49', '51 -> 74']
