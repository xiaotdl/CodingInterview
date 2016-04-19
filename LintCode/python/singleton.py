class Solution:
    # @return: The same instance of this class every time
    singleton = None
    @classmethod
    def getInstance(cls):
        if not cls.singleton:
            cls.singleton = Solution()
        return cls.singleton
