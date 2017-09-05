package permutations_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/4/17.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(depth*leafs), depth = n, leafs = n - 1
    // space: O(1)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> currRes, List<List<Integer>> res) {
        if (currRes.size() == nums.length) {
            res.add(new ArrayList<Integer>(currRes));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]
                    || i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            currRes.add(nums[i]);
            dfs(nums, visited, currRes, res);
            visited[i] = false;
            currRes.remove(currRes.size() - 1);
        }
    }
}
