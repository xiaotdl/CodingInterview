package generalized_abbreviation;

import java.util.*;

/**
 * Created by Xiaotian on 2/4/18.
 */
public class Solution {
    // tag: dfs, backtrack
    // time: O(2^n)
    // space: O(1)
    /**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();

        dfs(word, 0, "", 0, res);
        return res;
    }

    private void dfs(String word, int pos, String path, int count, List<String> res) {
        if (pos == word.length()) {
            if (count > 0) path += count;
            res.add(path);
            return;
        }

        // abbreviate curr char, cnt++
        dfs(word, pos + 1, path, count + 1, res);
        // not abbreviate curr char, append cnt&char, reset cnt
        dfs(word, pos + 1, path + (count > 0 ? count : "") + word.charAt(pos), 0, res);
    }
}
