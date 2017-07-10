package word_abbreviation;

import java.util.*;

/**
 * Created by Xiaotian on 7/10/17.
 */
public class Solution {
    // credit: https://discuss.leetcode.com/topic/82613/really-simple-and-straightforward-java-solution
    // tag: str
    // time: O(n^2*m), n: dict len, m: avg word len
    // space: O(1)
    public List<String> wordsAbbreviation(List<String> dict) {
        int n = dict.size();
        int[] prefix = new int[n]; // start index of the abbr
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            prefix[i] = 1;
            res[i] = getAbbr(dict.get(i), 1);
        }
        for (int i = 0; i < n; i++) {
            while (true) {
                Set<Integer> set = new HashSet<>();
                for (int j = i + 1; j < n; j++) {
                    // check all strings with the same abbr
                    if (res[j].equals(res[i])) set.add(j);
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k : set) {
                    prefix[k] += 1;
                    res[k] = getAbbr(dict.get(k), prefix[k]);
                }
            }
        }
        return Arrays.asList(res);
    }

    // k: start index of the abbr
    private String getAbbr(String s, int k) {
        if (k >= s.length() - 2) return s;
        StringBuffer sb = new StringBuffer();
        sb.append(s.substring(0, k));
        sb.append(s.length() - 1 - k);
        sb.append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}


class SolutionII {
    // credit: https://discuss.leetcode.com/topic/82571/verbose-java-solution-hashmap-s
    // classify words by length,
    //
}

