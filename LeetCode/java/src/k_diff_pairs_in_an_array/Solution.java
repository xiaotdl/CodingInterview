package k_diff_pairs_in_an_array;

import java.util.*;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class Solution {
    // tag: array, hash
    // time: O(n)
    // space: O(n)
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k < 0) return 0;

        Map<Integer, Integer> map = new HashMap<>(); // val2count
        for (int num : nums) {
            map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getKey();
            int cnt = entry.getValue();
            if (k == 0) {
                if (cnt >= 2) res++;
            }
            else {
                if (map.containsKey(val + k)) {
                    res++;
                }
            }
        }
        return res;
    }
}

class SolutionII {
    // sliding window
    // tag: array, ptr
    // time: O(nlogn)
    // space: O(1)
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return 0;

        Arrays.sort(nums);
        int res = 0;
        int l = 0;
        int r = 1;
        while (l < nums.length && r < nums.length) {
            if (l == r || nums[l] + k > nums[r]) {
                r++;
            }
            else if (nums[l] + k < nums[r]) {
                l++;
            }
            else {
                l++;
                res++;
                while (l < nums.length && nums[l] == nums[l - 1]) l++;
                r = Math.max(l + 1, r + 1);
            }
        }
        return res;
    }
}
