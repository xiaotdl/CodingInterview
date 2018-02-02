package factorization;

import java.util.*;

/**
 * Created by Xiaotian on 2/2/18.
 */
public class Solution {
    // TLE
    // Improvement: http://www.cnblogs.com/lz87/p/7494025.html
    // tag: dfs
    // time: O(?)
    // space: O(1)
    /*
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 1) return res;

        dfs(n, 2, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int n, int startFactor, List<Integer> tmpRes, List<List<Integer>> res) {
        if (n == 0) return;
        if (n == 1) {
            if (tmpRes.size() == 1) return;
            res.add(new ArrayList<Integer>(tmpRes));
            return;
        }

        for (int x = startFactor; x <= n; x++) {
            if (n % x != 0) continue;
            tmpRes.add(x);
            dfs(n/x, x, tmpRes, res);
            tmpRes.remove(tmpRes.size() - 1);
        }
    }
}
