package combination_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/24/16.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(depth*leafs), depth = n, leafs = n - 1
    // space: O(1)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<Integer>(), res);
        return res;
    }

    void dfs(int[] candidates, int target, int pos, int currSum, ArrayList<Integer> path, List<List<Integer>> res) {
        if (currSum == target) {
            res.add(new ArrayList<Integer>(path));
        }
        else if (currSum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            if (i != pos && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            dfs(candidates, target, i + 1, currSum + candidates[i], path, res);
            path.remove(path.size() - 1 );
        }
    }
}
