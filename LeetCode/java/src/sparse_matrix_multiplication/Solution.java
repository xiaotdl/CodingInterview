package sparse_matrix_multiplication;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */
class Solution {
    // XXX: TLE
    // tag: matrix, brutal force
    // time: O(mnl)
    // space: O(1)
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int l = B[0].length;

        int[][] C = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k]*B[k][j];
                }
            }
        }
        return C;
    }
}

class SolutionII {
    // Similar to Solution, but skipped A[i][k] == 0, which saves an iteration through a column of B.
    // tag: matrix, brutal force
    // time: O(mnl)
    // space: O(1)
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int l = B[0].length;

        int[][] C = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < l; j++) {
                    C[i][j] += A[i][k]*B[k][j];
                }
            }
        }
        return C;
    }
}

class SolutionIII {
    // On top of SolutionII, use bNonZeroIdx to record B cells with non-zero value
    // tag: matrix
    // time: O(mnl)
    // space: O(nl)
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int l = B[0].length;

        List<List<Integer>> bNonZeroIdx = new ArrayList<>(); // bNonZeroIdx[i][j] != 0
        for (int i = 0; i < n; i++) {
            bNonZeroIdx.add(new ArrayList<>());
            for (int j = 0; j < l; j++) {
                if (B[i][j] != 0) {
                    bNonZeroIdx.get(i).add(j);
                }
            }
        }

        int[][] C = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int p = 0; p < bNonZeroIdx.get(k).size(); p++) {
                    int j = bNonZeroIdx.get(k).get(p);
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
