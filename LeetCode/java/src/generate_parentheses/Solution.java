package generate_parentheses;

import java.util.*;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: str, dfs
    // time: O(2^2n), recursion tree height is 2n
    // space: O(2n), recursion tree height is 2n
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;

        dfs(n, n, new StringBuffer(), res);

        return res;
    }

    private void dfs(int leftN, int rightN, StringBuffer path, List<String> res) {
        if (leftN == 0 && rightN == 0) {
            res.add(path.toString());
            return;
        }

        if (leftN > rightN) {
            return;
        }

        if (leftN > 0) {
            path.append('(');
            dfs(leftN - 1, rightN, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (rightN > 0) {
            path.append(')');
            dfs(leftN, rightN - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
