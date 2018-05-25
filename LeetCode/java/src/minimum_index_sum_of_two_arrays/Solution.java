package minimum_index_sum_of_two_arrays;

import java.util.*;

/**
 * Created by Xiaotian on 3/19/18.
 */
class Solution {
    // tag: hash
    // time: O(n)
    // space: O(n)
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> m = new HashMap<>(); // word2idxSum
        for (int i = 0; i < list1.length; i++) {
            m.put(list1[i], i);
        }
        List<String> l = new ArrayList<String>();
        int minIdxSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            String currS = list2[i];
            if (m.containsKey(currS)) {
                m.put(currS, m.get(currS) + i);
                if (m.get(currS) < minIdxSum) {
                    minIdxSum = Math.min(minIdxSum, m.get(currS));
                    l = new ArrayList<String>();
                    l.add(currS);
                }
                else if (m.get(currS) == minIdxSum) {
                    l.add(currS);
                }
            }
        }
        String[] res = new String[l.size()];
        int i = 0;
        for (String s : l) res[i++] = s;
        return res;
    }
}
