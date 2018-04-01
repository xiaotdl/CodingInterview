package generate_anagrams;

import java.util.*;

/**
 * Created by Xiaotian on 3/19/18.
 */
class Solution {
    // Same as permutation
    // tag: dfs
    // time: O(2^n)
    // space: O(n)
    List<String> generate_anagrams(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, new boolean[s.length()], new StringBuilder(), res);
        return res;
    }

    private void dfs(String s, boolean[] visited, StringBuilder path, List<String> res) {
        if (path.length() == s.length()) {
            res.add(path.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) continue;
            path.append(s.charAt(i));
            visited[i] = true;
            dfs(s, visited, path, res);
            visited[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generate_anagrams("abc"));
    }
}

