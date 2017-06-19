package range_sum_query_2d_mutable;

/**
 * Created by Xiaotian on 6/19/17.
 */
public class Solution {

}

class NumMatrix {
    // 2D Binary Indexed Tree(BIT)
    // last bit: i & ((i - 1) ^ i)
    // tag: BIT
    // time:
    //   insert: O(logm*logn)
    //   search: O(logm*logn)
    // space: O(m*n)

    int[][] tree;
    int[][] nums;
    int[][] matrix;
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m + 1][n + 1];
        nums = new int[m][n];
        this.matrix = matrix;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int diff = val - nums[row][col];
        nums[row][col] = val;
        matrix[row][col] = val;
        for (int i = row + 1; i <= m; i += (i & ((i - 1) ^ i))) {
            for (int j = col + 1; j <= n; j += (j & ((j - 1) ^ j))) {
                tree[i][j] += diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return getSum(row2, col2) - getSum(row2, col1 - 1) - getSum(row1 - 1, col2) + getSum(row1 - 1, col1 - 1);
    }

    public int getSum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= (i & ((i - 1) ^ i))) {
            for (int j = col + 1; j > 0; j -= (j & ((j - 1) ^ j))) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
