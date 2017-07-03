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

    private void dfs(int leftN, int rightN, StringBuffer tmpRes, List<String> res) {
        if (leftN == 0 && rightN == 0) {
            res.add(tmpRes.toString());
            return;
        }

        if (leftN > rightN) {
            return;
        }

        if (leftN > 0) {
            tmpRes.append('(');
            dfs(leftN - 1, rightN, tmpRes, res);
            tmpRes.deleteCharAt(tmpRes.length() - 1);
        }
        if (rightN > 0) {
            tmpRes.append(')');
            dfs(leftN, rightN - 1, tmpRes, res);
            tmpRes.deleteCharAt(tmpRes.length() - 1);
        }
    }
}
