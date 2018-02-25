package custom_sort_string;

import java.util.*;

/**
 * Created by Xiaotian on 2/24/18.
 */
public class Solution {
    // tag: sort
    // time: O(n)
    // space: O(1)
    public String customSortString(String s, String t) {
        Character[] T = new Character[t.length()];
        int i = 0;
        for (char c : t.toCharArray()) {
            T[i++] = c;
        }
        Arrays.sort(T, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                Map<Character, Integer> m = new HashMap<>();
                int value = 1;
                for (char c : s.toCharArray()) {
                    m.put(c, value);
                    value++;
                }
                int c1Val = m.containsKey(c1) ? m.get(c1) : 0;
                int c2Val = m.containsKey(c2) ? m.get(c2) : 0;
                return c1Val - c2Val;
            }
        });

        String res = "";
        for (Character c : T) res += "" + c;
        return res;
    }
}
