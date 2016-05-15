package generate_parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: string, backtracking
    // time: O(2^2n), recursion tree height is 2n
    // space: O(2n), recursion tree height is 2n
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n <= 0) {
            return result;
        }

        StringBuilder tmpResult = new StringBuilder();
        helper(n, n, n, tmpResult, result);

        return result;
    }

    private void helper(int n, int leftN, int rightN, StringBuilder tmpResult, List<String>result) {
        if (leftN == 0 && rightN == 0) {
            result.add(tmpResult.toString());
            return;
        }

        if (leftN > rightN) {
            return;
        }

        if (leftN > 0) {
            tmpResult.append("(");
            helper(n, leftN - 1, rightN, tmpResult, result);
            tmpResult.deleteCharAt(tmpResult.length() - 1);
        }

        if (rightN > 0) {
            tmpResult.append(")");
            helper(n, leftN, rightN - 1, tmpResult, result);
            tmpResult.deleteCharAt(tmpResult.length() - 1);
        }
    }
}
