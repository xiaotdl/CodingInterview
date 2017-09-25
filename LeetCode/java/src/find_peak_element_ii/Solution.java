package find_peak_element_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/24/17.
 */
public class Solution {
    // tag: binary search
    // time: O(m+n)
    // space: O(1)
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0 || nums[0].length == 0) return res;

        return find(nums, 0, nums.length - 1, 0, nums[0].length - 1, true);
    }

    private List<Integer> find(int[][] nums, int rowL, int rowR, int colL, int colR, boolean reduceRows) {
        if (reduceRows) { // reduce rows
            int m = rowL + (rowR - rowL) / 2;

            int maxColIndex = colL;
            for (int i = colL; i <= colR; i++) {
                if (nums[m][i] > nums[m][maxColIndex]) {
                    maxColIndex = i;
                }
            }

            if (nums[m - 1][maxColIndex] > nums[m][maxColIndex]) {
                return find(nums, rowL, m - 1, colL, colR, !reduceRows);
            } else if (nums[m][maxColIndex] < nums[m + 1][maxColIndex]) {
                return find(nums, m + 1, rowR, colL, colR, !reduceRows);
            } else {
                return new ArrayList<Integer>(Arrays.asList(m, maxColIndex));
            }
        } else { // reduce cols
            int m = colL + (colR - colL) / 2;

            int maxRowIndex = rowL;
            for (int i = rowL; i <= rowR; i++) {
                if (nums[i][m] > nums[maxRowIndex][m]) {
                    maxRowIndex = i;
                }
            }

            if (nums[maxRowIndex][m - 1] > nums[maxRowIndex][m]) {
                return find(nums, rowL, rowR, colL, m - 1, !reduceRows);
            } else if (nums[maxRowIndex][m] < nums[maxRowIndex][m + 1]) {
                return find(nums, rowL, rowR, m + 1, colR, !reduceRows);
            } else {
                return new ArrayList<Integer>(Arrays.asList(maxRowIndex, m));
            }
        }
    }
}
