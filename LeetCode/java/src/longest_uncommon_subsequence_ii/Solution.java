package longest_uncommon_subsequence_ii;

import java.util.*;

/**
 * Created by Xiaotian on 7/9/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n * 2^m)
    // space: O(n * 2^m), n: num of strs, m: avr str length
    public int findLUSlength(String[] strs) {
        Map<String, Integer> m = new HashMap<>(); // subStr2cnt
        for (String s : strs) {
            for (String subSeq : getSubSeqs(s)) {
                m.put(subSeq, m.containsKey(subSeq) ? m.get(subSeq) + 1 : 1);
            }
        }
        int max = -1; // longestSingleSubSeqLen
        for (String subStr : m.keySet()) {
            if (m.get(subStr) == 1) {
                max = Math.max(max, subStr.length());
            }
        }
        return max;
    }

//    private Set<String> getSubSeqs(String s) {
//        Set<String> res = new HashSet<>();
//        if (s.length() == 0) {
//            res.add("");
//            return res;
//        }
//
//        Set<String> subSeqs = getSubSeqs(s.substring(1));
//        res.addAll(subSeqs);
//        for (String subSeq : subSeqs) {
//            res.add(s.charAt(0) + subSeq);
//        }
//        return res;
//    }

    private Set<String> getSubSeqs(String word) {
        Set<String> res = new HashSet<>();
        int n = word.length();
        // Number of subsequences is (2**n - 1)
        int opsize = (int) Math.pow(2, n);

        // Run from counter 000..0 to 111..1
        for (int counter = 0; counter < opsize; counter++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < n; j++) {
                /* Check if jth bit in the counter is set
                    If set then print jth element from arr[] */
                if ((counter & (1 << j)) != 0)
                    sb.append(word.charAt(j));
            }
            res.add(sb.toString());
        }
        return res;
    }
}

class SolutionII {
    // Ref: https://leetcode.com/problems/longest-uncommon-subsequence-ii/discuss/99443/Java(15ms)-Sort-+-check-subsequence
    // tag: str
    // time: O(n^2)
    // space: O(1)
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });

        Set<String> duplicates = getDuplicates(strs);
        for (int i = 0; i < strs.length; i++) {
            if (duplicates.contains(strs[i])) continue;
            if (i == 0) return strs[0].length();
            for (int j = 0; j < i; j++) {
                if (isSubsequence(strs[i], strs[j])) break;
                if (j == i - 1) return strs[i].length();
            }
        }
        return -1;
    }

    private Set<String> getDuplicates(String[] strs) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String s : strs) {
            if (seen.contains(s)) {
                duplicates.add(s);
            }
            else {
                seen.add(s);
            }
        }
        return duplicates;
    }

    private boolean isSubsequence(String subseq, String s) {
        int i = 0;
        int j = 0;
        while (i < subseq.length() && j < s.length()) {
            if (subseq.charAt(i) == s.charAt(j)) i++;
            j++;
        }
        return i == subseq.length();
    }
}
