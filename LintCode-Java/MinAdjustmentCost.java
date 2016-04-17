import java.util.ArrayList;

public class MinAdjustmentCost {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */

    // V1, O(n*100*target)
    // DP(matrix, backtrack)
    public static int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        if (A == null || A.size() == 0) {
            return 0;
        }

        // f[i][v]: 把index = i的值修改为v，所需要的最小花费
        int[][] f = new int[A.size()][101];

        for (int i = 0; i <= 100 ; i++) {
            f[0][i] = Math.abs(i - A.get(0));
        }

        for (int i = 1; i < A.size(); i++) {
            for (int j = 1; j <= 100; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= 100; k++) {
                    // 不符合条件
                    if (Math.abs(j - k) > target) {
                        continue;
                    }

                    int cost = Math.abs(j - A.get(i)) + f[i - 1][k];
                    f[i][j] = Math.min(f[i][j], cost);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            res = Math.min(res, f[A.size() - 1][i]);
        }
        return res;
    }
}
