package pascals_triangle_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: array, dp
// time: O(n^2)
// space: O(1)
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                int val = getVal(res, j - 1) + getVal(res, j);
                if (j == i) {
                    res.add(val);
                }
                else {
                    res.set(j, val);
                }
            }
        }
        return res;
    }

    public int getVal(List<Integer> res, int i) {
        if (i < 0 || i >= res.size()) return 0;
        return res.get(i);
    }
}
