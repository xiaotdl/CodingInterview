package pascals_triangle_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class Solution {
    // tag: array, dp
    // time: O(n^2)
    // space: O(1)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;

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

class SolutionII {
    // same as SolutionI
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        if (rowIndex < 0) return row;

        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                int val = (j == 0 ? 0 : row.get(j - 1)) + (j == i ? 0 : row.get(j));
                if (j == i) {
                    row.add(val);
                }
                else {
                    row.set(j, val);
                }
            }
        }
        return row;
    }
}
