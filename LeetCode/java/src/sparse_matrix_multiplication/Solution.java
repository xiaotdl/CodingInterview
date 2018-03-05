package sparse_matrix_multiplication;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: matrix
    // time: O(mnl)
    // space: O(m+n)
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int l = A[0].length;
        int n = B[0].length;

        Set<Integer> aRowAllZeroes = new HashSet<>();
        for (int i = 0; i < m; i++) {
            boolean rowAllZeroes = true;
            for (int j = 0; j < l; j++) {
                if (A[i][j] != 0) rowAllZeroes = false;
            }
            if (rowAllZeroes) aRowAllZeroes.add(i);
        }

        Set<Integer> bColAllZeroes = new HashSet<>();
        for (int j = 0; j < n; j++) {
            boolean colAllZeroes = true;
            for (int i = 0; i < l; i++) {
                if (B[i][j] != 0) colAllZeroes = false;
            }
            if (colAllZeroes) bColAllZeroes.add(j);
        }

        int[][] C = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (aRowAllZeroes.contains(i) || bColAllZeroes.contains(j)) continue;
                for (int k = 0; k < l; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
