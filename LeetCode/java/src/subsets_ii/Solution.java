package subsets_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */

class Solution {
    // tag: array, dfs, backtracking
    // time: O(2^n)
    // space: O(n)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, path, res);
        return res;
    }

    private void dfs(int[] nums, int pos, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = pos; i < nums.length; i++) {
            // skip duplicates on the same tree level
            if (i != pos && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
