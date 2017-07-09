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

