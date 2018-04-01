package subsets;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */
class Solution {
    // tag: array, dfs, backtracking
    // time: O(2^n)
    // space: O(n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, path, res);
        return res;
    }

    private void dfs(int[] nums, int pos, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = pos; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}

class SolutionII {
    // tag: bit
    // time: O(2^n)
    // space: O(1)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // check whether nums[j] is in this subset
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);
        }
        return res;
    }
}

class SolutionIII {
    // Simlimar to Solution
    // tag: array, dfs, backtracking
    // time: O(2^n)
    // space: O(1)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, int pos, List<Integer> path, List<List<Integer>> res) {
        if (pos == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        // doesn't pick curr num
        dfs(nums, pos + 1, path, res);

        // pick curr num
        path.add(nums[pos]);
        dfs(nums, pos + 1, path, res);
        path.remove(path.size() - 1);
    }
}
