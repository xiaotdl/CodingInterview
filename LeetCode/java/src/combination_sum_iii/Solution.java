package combination_sum_iii;

import java.util.*;

/**
 * Created by Xiaotian on 12/24/16.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(9k)
    // space: O(k)
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        dfs(candidates, k, n, 0, 0, new ArrayList<Integer>(), res);
        return res;
    }

    void dfs(int[] candidates, int left, int target, int pos, int currSum, ArrayList<Integer> currRes, List<List<Integer>> res) {
        if (currSum == target && left == 0) {
            res.add(new ArrayList<Integer>(currRes));
        }
        else if (left < 0 || currSum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            currRes.add(candidates[i]);
            dfs(candidates, left - 1, target, i + 1, currSum + candidates[i], currRes, res);
            currRes.remove(currRes.size() - 1);
        }
    }

}
