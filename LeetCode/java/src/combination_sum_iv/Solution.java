package combination_sum_iv;

import java.util.*;

/**
 * Created by Xiaotian on 12/24/16.
 */
// MLE, TLE
// tag: dfs
// time: O(?)
// space: O(?)
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        dfs(nums, target, 0, 0, new ArrayList<Integer>(), res);
        return res.size();
    }

    void dfs(int[] candidates, int target, int pos, int currSum, ArrayList<Integer> currRes, List<List<Integer>> res) {
        if (currSum == target) {
            res.add(new ArrayList<Integer>(currRes));
        }
        else if (currSum > target) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            currRes.add(candidates[i]);
            dfs(candidates, target, pos, currSum + candidates[i], currRes, res);
            currRes.remove(currRes.size() - 1 );
        }
    }
}

// tag: dp
// time: O(n*target)
// space: O(target)
class SolutionII {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num: nums) {
                if (num > i) break;
                else if (num == i) dp[i] += 1;
                else dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }

}
