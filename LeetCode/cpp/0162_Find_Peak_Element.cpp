// tag: brutal force
// time: O(n)
// space: O(1)
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            if ((i == 0 || nums[i - 1] < nums[i]) && (i == nums.size() - 1 || nums[i] > nums[i + 1])) {
                return i;
            }
        }
        return -1;
    }
};

// tag: binary search
// time: O(logn)
// space: O(1)
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int l = 0;
        int r = nums.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if ((m == 0 || nums[m - 1] < nums[m]) && (m == nums.size() - 1 || nums[m] > nums[m + 1])) {
                return m;
            }
            if (m == 0 || nums[m - 1] < nums[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        
        return -1;
    }
};
