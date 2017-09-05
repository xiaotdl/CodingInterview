package palindrom_partitioning;

import java.util.*;

/**
 * Created by Xiaotian on 12/28/16.
 */
public class Solution {
    // tag: array, dfs, backtracking
    // time: O(depth*leafs), depth = n, leafs = n - 1
    // space: O(1)
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        dfs(s, 0, new ArrayList<String>(), res);

        return res;
    }

    public void dfs(String s, int pos, List<String> currRes, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<String>(currRes));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (isPalindrome(s, pos, i)) {
                currRes.add(s.substring(pos, i + 1));
                dfs(s, i + 1, currRes, res);
                currRes.remove(currRes.size() - 1);
            };
        }
    }

    public boolean isPalindrome(String s, int l, int r) {
        if (l > r) return false;
        boolean isPalindrom = true;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                isPalindrom = false;
            }
            l++;
            r--;
        }
        return isPalindrom;
    }
}
