package palindrome_permutation_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/28/18.
 */
public class Solution {
    // canPermutePalindrome + getUniquePermutations + validatePalindrome
    // tag: dfs
    // time: O(n!)
    // space: O(n)
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (!canPermutePalindrome(s)) return res;

        // get all permutations
        char[] S = s.toCharArray();
        boolean[] visited = new boolean[S.length];
        Arrays.sort(S);
        dfs(S, visited, new StringBuilder(), res);
        return res;
    }

    private void dfs(char[] S, boolean[] visited, StringBuilder path, List<String> res) {
        if (path.length() == S.length) {
            String candidate = path.toString();
            if (isPalindrome(candidate)) {
                res.add(candidate);
            }
            return;
        }
        for (int i = 0; i < S.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && S[i] == S[i - 1] && visited[i - 1] == false) continue;
            visited[i] = true;
            path.append(S[i]);
            dfs(S, visited, path, res);
            visited[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    private boolean canPermutePalindrome(String s) {
        int singleCharCnt = 0;
        int[] charCnt = new int[256];
        for (char c : s.toCharArray()) {
            if (charCnt[c] == 0) {
                charCnt[c]++;
                singleCharCnt++;
            }
            else {
                charCnt[c]--;
                singleCharCnt--;
            }
        }
        return singleCharCnt <= 1;
    }
}
