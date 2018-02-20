package word_sorting;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // tag: sort
    // time: O(nlogn)
    // space: O(1)
    /**
     * @param alphabet: the new alphabet
     * @param words: the original string array
     * @return: the string array after sorting
     */
    public String[] wordSort(char[] alphabet, String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char[] S1 = s1.toCharArray();
                char[] S2 = s2.toCharArray();
                Map<Character, Integer> m = new HashMap<>(); // char2val
                int x = 0;
                for (char c : alphabet) {
                    m.put(c, x++);
                }
                int i = 0, j = 0;
                while (i < S1.length && j < S2.length) {
                    if (m.get(S1[i]) == m.get(S2[j])) {
                        i++;
                        j++;
                    } else {
                        return m.get(S1[i]) - m.get(S2[j]);
                    }
                }
                if (i < S1.length) return 1;
                if (j < S2.length) return -1;
                return 0;
            }
        });
        return words;
    }
}
