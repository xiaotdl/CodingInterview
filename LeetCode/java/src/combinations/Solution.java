package combinations;

import java.util.*;

/**
 * Created by Xiaotian on 2/11/18.
 */
public class Solution {
    /*
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) return res;

        List<Integer> path = new ArrayList<>();
        dfs(n, k, 1, path, res, "");
        return res;
    }

    private void dfs(int n, int k, int pos, List<Integer> path, List<List<Integer>> res, String lvl) {
        if (path.size() == k) {
            res.add(new ArrayList<Integer>(path));
            System.out.println(path);
            return;
        }

        for (int i = pos; i <= n; i++) {
            System.out.println(lvl + i);
            path.add(i);
            dfs(n, k, i + 1, path, res, lvl+"  ");
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(3, 3));
    }
}
