package remove_duplicate_letters;

import java.util.*;

/**
 * Created by Xiaotian on 4/9/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/remove-duplicate-letters/discuss/76768/A-short-O(n)-recursive-greedy-solution
    // tag: greedy
    // time: O(26*n)
    // space: O(1)
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";

        char[] S = s.toCharArray();
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < S.length; i++) cnt[S[i] - 'a']++;
        for (int i = 0; i < S.length; i++) {
            if (S[i] < S[pos]) pos = i;
            cnt[S[i] - 'a']--;
            if (cnt[S[i] - 'a'] == 0) break;
        }
        return S[pos] + removeDuplicateLetters(s.substring(pos + 1).replaceAll(""+S[pos], ""));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("abcacb"));
        System.out.println(new Solution().removeDuplicateLetters("bccbaa"));
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/remove-duplicate-letters/discuss/76762/Java-O(n)-solution-using-stack-with-detail-explanation
    // tag: stack
    // time: O(n)
    // space: O(n)
    public String removeDuplicateLetters(String s) {
        char[] S = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int[] charCnt = new int[26];
        for (char c : S) {
            charCnt[c - 'a']++;
        }

        boolean[] visited = new boolean[26];
        for (char c : S) {
            charCnt[c - 'a']--;
            if (visited[c - 'a']) continue;

            while (!stack.isEmpty() && stack.peek() > c && charCnt[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("bcabc"));
    }
}

