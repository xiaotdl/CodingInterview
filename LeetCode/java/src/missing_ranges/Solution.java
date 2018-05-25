package missing_ranges;

import java.util.*;

/**
 * Created by Xiaotian on 1/20/18.
 */
class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            addRange(lower, upper, res);
            return res;
        }

        addRange(lower, (long) nums[0] - 1, res);

        for (int i = 1; i < nums.length; i++) {
            addRange((long) nums[i - 1] + 1, (long) nums[i] - 1, res);
        }

        addRange((long) nums[nums.length - 1] + 1, upper, res);

        return res;
    }

    private void addRange(long lower, long upper, List<String> res) {
        if (lower > upper) return;

        if (lower == upper) {
            res.add("" + lower);
        }
        else {
            res.add(lower + "->" + upper);
        }
    }
}
