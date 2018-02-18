package letter_case_permutation;

import java.util.*;

/**
 * Created by Xiaotian on 2/17/18.
 */
public class Solution {
    // tag: dfs
    // time: O(2^n)
    // space: O(1)
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if (S == null) return res;
        dfs(S, 0, "", res);
        return res;
    }

    private void dfs(String S, int pos, String path, List<String> res) {
        if (path.length() == S.length()) {
            res.add(path);
            return;
        }

        char c = S.charAt(pos);
        if (Character.isLetter(c)) {
            dfs(S, pos + 1, path + Character.toLowerCase(c), res);
            dfs(S, pos + 1, path + Character.toUpperCase(c), res);
        }
        else {
            dfs(S, pos + 1, path + c, res);
        }
    }
}
