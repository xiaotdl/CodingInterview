package k_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 2/10/18.
 */
public class Solution {
    // tag: dfs, backtrack
    // time: O(n^k)
    // space: O(1)
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param targer: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (A == null || A.length < k) return res;

        Arrays.sort(A);

        dfs(A, k, target, 0, new ArrayList<Integer>(), res, "");
        return res;
    }

    public void dfs(int[] A, int k, int target, int pos, List<Integer> path, List<List<Integer>> res, String lvl) {
        lvl += "  ";
        if (target < 0) return;
        if (path.size() > k) return;
        if (path.size() == k && target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = pos; i < A.length; i++) {
            path.add(A[i]);
            System.out.println(lvl+i);
            System.out.println(path);
            dfs(A, k, target - A[i], i + 1, path, res, lvl);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kSumII(new int[]{0,1,2,3}, 2, 100));
    }
}


