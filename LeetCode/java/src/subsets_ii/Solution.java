package subsets_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(depth*leafs), depth = n, leafs = n - 1
    // space: O(1)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, int pos, List<Integer> currRes, List<List<Integer>> res) {
        res.add(new ArrayList<>(currRes));

        for (int i = pos; i < nums.length; i++) {
            // skip duplicates on the same tree level
            if (i != pos && nums[i] == nums[i - 1]) continue;

            currRes.add(nums[i]);
            dfs(nums, i + 1, currRes, res);
            currRes.remove(currRes.size() - 1);
        }
    }
}
