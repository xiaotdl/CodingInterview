package find_the_duplicate_number;

/**
 * Created by Xiaotian on 11/1/16.
 */
public class Solution {
    // credit: https://discuss.leetcode.com/topic/25580/two-solutions-with-explanation-o-nlog-n-and-o-n-time-o-1-space-without-changing-the-input-array
    // tag: binary search
    // time: O(nlogn), find duplicate in logn times, each time loop through n nums
    // space: O(1)
    /*
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (smallerNumsCnt(nums, m) < m) {
                l = m;
            } else {
                r = m;
            }
        }
        if (isDuplicate(nums, l)) return l;
        if (isDuplicate(nums, r)) return r;
        return -1;
    }

    private int smallerNumsCnt(int[] nums, int target) {
        int cnt = 0;
        for (int num : nums) {
            if (num < target) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isDuplicate(int[] nums, int target) {
        int cnt = 0;
        for (int num : nums) {
            if (num == target) {
                cnt++;
                if (cnt == 2) return true;
            }
        }
        return false;
    }
}

class SolutionII {
    // Similar to LC144 Linked List Circle II
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                int p1 = 0;
                int p2 = slow;
                while (p1 != p2) {
                    p1 = nums[p1];
                    p2 = nums[p2];
                }
                return p1;
            }
        }
    }
}
