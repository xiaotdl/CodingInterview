package assign_cookies;

import java.util.*;

/**
 * Created by Xiaotian on 2/21/18.
 */
public class Solution {
    // tag: greedy
    // time: O(nlogn)
    // space: O(1)
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int cnt = 0;
        int i = 0; // g/child idx
        int j = 0; // s/cookie idx
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                cnt++;
            }
            j++;
        }
        return cnt;
    }
}
