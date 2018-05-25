package expression_add_operators;

import java.util.*;

/**
 * Created by Xiaotian on 4/25/18.
 */
public class Solution {
    // tag: dfs
    // time: O(3^n), n: len(num)
    // space: O(n), stack space
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;

        dfs(num, target, 0, 0, 0, "", res);
        return res;
    }

    // NOTE: used long for val and factor as it might overflow beyond Integer.MAX_VALUE
    private void dfs(String num, int target, int pos, long val, long factor, String path, List<String> res) {
        if (pos == num.length()) {
            if (val == target) {
                res.add(path);
            }
            return;
        }

        for (int i = pos; i < num.length(); i++) {
            if (i > pos && num.charAt(pos) == '0') break; // NOTE: No leading multiple 0 digits

            long currVal = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                dfs(num, target, i + 1, currVal, currVal, path + currVal, res);
            }
            else {
                dfs(num, target, i + 1, val + currVal, currVal, path + "+" + currVal, res);
                dfs(num, target, i + 1, val - currVal, -currVal, path + "-" + currVal, res);
                dfs(num, target, i + 1, val - factor + factor*currVal, factor*currVal, path + "*" + currVal, res);
            }
        }
    }
}
