package substring_with_concatenation_of_all_words;

import java.util.*;

/**
 * Created by Xiaotian on 6/16/17.
 */
public class Solution {
    // TODO
    // credit: https://discuss.leetcode.com/topic/17943/naive-c-solution-using-two-unordered_map-about-20-lines/7
    // TLE
    // tag: hash, ptr, sliding window
    // time: O(n*words.size)
    // space: O(words.size)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.put(w, cnt.containsKey(w) ? cnt.get(w) + 1 : 1);
        }
        int n = s.length();
        int num = words.length;
        int len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for(; j < num; j++) {
                String w = s.substring(i + j * len, i + j * len + len);
                if (cnt.containsKey(w)) {
                    seen.put(w, seen.containsKey(w) ? seen.get(w) + 1 : 1);
                    if (seen.get(w) > cnt.get(w)) break;
                }
                else break;
            }
            if (j == num) res.add(i);
        }
        return res;
    }
}

class SolutionII {
    // TODO
    // credit: http://www.programcreek.com/2014/06/leetcode-substring-with-concatenation-of-all-words-java/
    // tag: hash, ptr, sliding window
    // time: O(n)
    // space: O(n)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>(); // word2cnt
        for (String w: words) {
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        }

        int len = words[0].length();

        for (int j = 0; j < len; j++) {
            Map<String, Integer> currentMap = new HashMap<>(); // word2cnt
            int start = j; //start index of start
            int count = 0; //count total qualified words so far

            for (int i = j; i <= s.length() - len; i = i + len) {
                String sub = s.substring(i, i + len);
                if (map.containsKey(sub)) {
                    currentMap.put(sub, currentMap.containsKey(sub) ? currentMap.get(sub) + 1 : 1);

                    count++;

                    while (currentMap.get(sub) > map.get(sub)) {
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);

                        count--;
                        start = start + len;
                    }


                    if (count == words.length) {
                        result.add(start); //add to result

                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start + len);
                        currentMap.put(left, currentMap.get(left) - 1);
                        count--;
                        start = start + len;
                    }
                }
                else {
                    currentMap.clear();
                    start = i + len;
                    count = 0;
                }
            }
        }

        return result;
    }
}
