package pascals_triangle;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: array
// time: O(n^2)
// space: O(1)
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;

        List<Integer> row = new ArrayList<>();
        row.add(1);
        res.add(row);

        for (int i = 1; i < numRows; i++) {
            row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // res[i][j] = getVal(res, i - 1, j - 1) + getVal(res, i - 1, j);
                row.add(getVal(res, i - 1, j - 1) + getVal(res, i - 1, j));
            }
            res.add(row);
        }
        return res;
    }

    public int getVal(List<List<Integer>> res, int row, int col) {
        if (row < 0 || row >= res.size()) return 0;
        if (col < 0 || col >= res.get(row).size()) return 0;
        return res.get(row).get(col);
    }
}
