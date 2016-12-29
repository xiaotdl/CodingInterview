package palindrom_partitioning;

import java.util.*;

/**
 * Created by Xiaotian on 12/28/16.
 */
// tag: dfs
// time: O(n!)
// space: O(1)
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        dfs(s, 0, new ArrayList<String>(), res);

        return res;
    }

    public void dfs(String s, int pos, List<String> tmpRes, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<String>(tmpRes));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (isPalindrome(s, pos, i)) {
                tmpRes.add(s.substring(pos, i + 1));
                dfs(s, i + 1, tmpRes, res);
                tmpRes.remove(tmpRes.size() - 1);
            };
        }
    }

    public boolean isPalindrome(String s, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) return false;
        boolean isPalindrom = true;
        int i = fromIndex;
        int j = toIndex;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                isPalindrom = false;
            }
            i++;
            j--;
        }
        return isPalindrom;
    }
}
