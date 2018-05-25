package combination_sum_iv;

import java.util.*;

/**
 * Created by Xiaotian on 12/24/16.
 */
public class Solution {
    // MLE, TLE
    // tag: dfs
    // time: O(?)
    // space: O(?)
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        dfs(nums, target, 0, 0, new ArrayList<Integer>(), res);
        return res.size();
    }

    void dfs(int[] candidates, int target, int pos, int currSum, ArrayList<Integer> path, List<List<Integer>> res) {
        if (currSum == target) {
            res.add(new ArrayList<Integer>(path));
        }
        else if (currSum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, target, pos, currSum + candidates[i], path, res);
            path.remove(path.size() - 1 );
        }
    }
}


class SolutionII {
    // Same as BackpackVI
    // nums和target反着遍历，每次遍历target，都遍历所有的数nums
    // 重复选择+装满可能性总数 (+不同排列)
    // dp[i]: numbers of combinations from given backpack that sums to i. dp[i] += dp[i - nums[j]] for j in backpack

    // tag: dp
    // time: O(n*target)
    // space: O(target)
    public int combinationSum4(int[] nums, int target) {
        // dp[i]: permutation ways to pick a few from A items with a total size of i
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                dp[i] += (i >= num ? dp[i - num] : 0);
            }
        }
        return dp[target];
    }
}
