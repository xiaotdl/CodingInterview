#class SVNRepo:
#    @classmethod
#    def isBadVersion(cls, id)
#        # Run unit tests to check whether verison `id` is a bad version
#        # return true if unit tests passed else false.
# You can use SVNRepo.isBadVersion(10) to check whether version 10 is a
# bad version.
class SVNRepo:
    @classmethod
    def isBadVersion(cls, id):
        if id >= 4:
            return True
        else:
            return False


class Solution:
    """
    @param n: An integers.
    @return: An integer which is the first bad version.
    """
    def findFirstBadVersion(self, n):
        # binary search
        # time:  O(logn)
        # space: O(1)
        if not n:
            return 0

        start = 1
        end = n
        while start + 1 < end:
            mid = start + (end - start) / 2
            ret = SVNRepo.isBadVersion(mid)
            if ret:
                end = mid
            else:
                start = mid

        if SVNRepo.isBadVersion(start):
            return start
        elif SVNRepo.isBadVersion(end):
            return end
        else:
            return 0


if __name__ == '__main__':
    print Solution().findFirstBadVersion(5)
    print Solution().findFirstBadVersion(10)
    print Solution().findFirstBadVersion(3)
# >>>
# 4
# 4
# 0
