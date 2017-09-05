package permutations;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(depth*leafs), depth = n, leafs = n - 1
    // space: O(1)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // Arrays.sort(nums); // not necessary
        dfs(nums, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, List<Integer> currRes, List<List<Integer>> res) {
        if (currRes.size() == nums.length) {
            res.add(new ArrayList<Integer>(currRes));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // element already exists, skip
            if (currRes.contains(nums[i])) continue;

            currRes.add(nums[i]);
            dfs(nums, currRes, res);
            currRes.remove(currRes.size() - 1);
        }
    }
}
