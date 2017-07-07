package palindrome_pairs;

import java.util.*;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(1)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;

        Map<String, Integer> m = new HashMap<>(); // word2index
        for (int i = 0; i < words.length; i++) {
            m.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                // e.g.
                // word1=s1+s2
                // word2=reversed_s2
                // word2+word1=reversed_s2+s1+s2
                // in case s1 is palindrome, word2+word1 is palindrome
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);
                if (isPalindrome(s1)) {
                    String reversed_s2 = new StringBuffer(s2).reverse().toString();
                    if (m.containsKey(reversed_s2) && m.get(reversed_s2) != i) {
                        List<Integer> l = new ArrayList<>();
                        l.add(m.get(reversed_s2));
                        l.add(i);
                        res.add(l);
                    }
                }
                // word1=s1+s2; word2=reversed_s1
                // word1+word2=s1+s2+reversed_s1
                if (s2.length() != 0 && isPalindrome(s2)) {
                    String reversed_s1 = new StringBuffer(s1).reverse().toString();
                    if (m.containsKey(reversed_s1) && m.get(reversed_s1) != i) {
                        List<Integer> l = new ArrayList<>();
                        l.add(i);
                        l.add(m.get(reversed_s1));
                        res.add(l);
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
