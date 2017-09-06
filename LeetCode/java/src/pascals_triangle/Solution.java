package pascals_triangle;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class Solution {
    // tag: array
    // time: O(n^2)
    // space: O(1)
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

class SolutionII {
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows <= 0) return triangle;

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                }
                else {
                    row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}
