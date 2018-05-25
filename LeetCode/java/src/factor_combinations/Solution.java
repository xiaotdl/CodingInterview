package factor_combinations;

import java.util.*;

/**
 * Created by Xiaotian on 2/2/18.
 */
class Solution {
    // tag: dfs, math
    // time: O(V+E)
    // space: O(1)
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 1) return res;

        dfs(n, 2, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int n, int startFactor, List<Integer> path, List<List<Integer>> res) {
        if (n == 1) {
            if (path.size() == 1) return;
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int x = startFactor; x <= n; x++) {
            if (n % x != 0) continue;
            path.add(x);
            dfs(n/x, x, path, res);
            path.remove(path.size() - 1);
        }
    }
}

class SolutionII {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), n, 2);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> current, int remain, int start) {
        for (int i = start; i * i <= remain; i++) {
            if (remain % i == 0) {
                current.add(i);
                current.add(remain / i);
                result.add(new ArrayList<>(current));
                current.remove(current.size() - 1);
                dfs(result, current, remain / i, i);
                current.remove(current.size() - 1);
            }
        }
    }
}

