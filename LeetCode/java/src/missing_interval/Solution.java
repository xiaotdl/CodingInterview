package missing_interval;

import java.util.*;

/**
 * Created by Xiaotian on 1/20/18.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            addRange(res, lower, upper);
            return res;
        }

        addRange(res, lower, (long) nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(res, (long) nums[i - 1] + 1, (long) nums[i] - 1);
        }

        addRange(res, (long) nums[nums.length - 1] + 1, upper);

        return res;
    }

    void addRange(List<String> l, long s, long e) {
        if (s > e) {
            return;
        }
        if (s == e) {
            l.add(s + "");
            return;
        }
        l.add(s + "->" + e);
    }
}
