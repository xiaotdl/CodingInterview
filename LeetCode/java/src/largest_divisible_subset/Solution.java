package largest_divisible_subset;

import java.util.*;

/**
 * Created by Xiaotian on 12/28/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        // dp[i]: finds lenOfLDS including nums[i]
        // index[i]: the index of largest element that can be fully divided by nums[i];
        //           index[i] points to nums[i]'s largest factor in nums
        int[] dp = new int[nums.length];
        int[] index = new int[nums.length];
        dp[0] = 1;
        Arrays.fill(index, -1);

        int max = 0;
        int maxIndex = -1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        int i = maxIndex;
        while (i >= 0) {
            res.add(nums[i]);
            i = index[i];
        }

        Collections.sort(res);
        return res;
    }
}
