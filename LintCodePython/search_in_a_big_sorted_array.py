class ArrayReader:
    def get(self, index):
        # this would return the number on the given index
        # if there is no number on that index, return -1
        arr = [1, 3, 6, 9, 21]
        try:
            return arr[index]
        except:
            return -1


class Solution:
    # @param {ArrayReader} reader: An instance of ArrayReader
    # @param {int} target an integer
    # @return {int} an integer
    def searchBigSortedArray(self, reader, target):
        # binary search
        # time:  O(logk), k is the first index of the given target number.
        # space: O(1)
        if not reader or target is None:
            return -1

        index = 0
        while reader.get(index) != -1 and reader.get(index) < target:
            index = index * 2 + 1

        start = (index + 1) / 2
        end = index
        while start + 1 < end:
            mid = start + (end - start) / 2
            if reader.get(mid) == -1:
                end = mid
            elif reader.get(mid) < target:
                start = mid
            elif reader.get(mid) > target:
                end = mid
            else:
                end = mid

        if reader.get(start) == target:
            return start
        elif reader.get(end) == target:
            return end
        else:
            return -1


if __name__ == '__main__':
    print Solution().searchBigSortedArray(ArrayReader(), 1)
    print Solution().searchBigSortedArray(ArrayReader(), 21)
# >>>
# 4
