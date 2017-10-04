package stone_game_ii;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // tag: dp
    // time: O(n^3)
    // space: O(n^2)
    /*
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame2(int[] A) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        // dp[i][j]: merge stones from i to j, max score
        int[][] dp = new int[2*n][2*n];
        boolean[][] isVisited = new boolean[2*n][2*n];

        int[][] sum = new int[2*n][2*n];
        for (int i = 0; i < 2*n; i++) {
            sum[i][i] = A[i%n];
            for (int j = i + 1; j < 2*n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j%n];
            }
        }

        for (int len = 2; len <= n; len++) {
            int i, j;
            for (i = 0, j = i + len - 1; j < 2*n; i++, j++) {
                memSearch(dp, isVisited, sum, i, j);
            }
        }

//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
//            System.out.println(i + "," + (i+n-1) + "->" + dp[i][i + n - 1]);
            min = Math.min(min, dp[i][i + n - 1]);
        }
        return min;
    }

    private int memSearch(int[][] dp, boolean[][] isVisited, int[][] sum, int l, int r) {
        if (isVisited[l][r]) {
            return dp[l][r];
        }
        isVisited[l][r] = true;

        if (l == r) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = l; k < r; k++) { // k < r as there is k-1 interval
            min = Math.min(
                min,
                sum[l][r]
                + memSearch(dp, isVisited, sum, l, k)
                + memSearch(dp, isVisited, sum, k + 1, r)
            );
        }
        dp[l][r] = min;
        return dp[l][r];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().stoneGame2(new int[]{1, 2, 3}));
        // 0 3 9 0 0 0
        // 0 0 5 10 0 0
        // 0 0 0 4 9 0
        // 0 0 0 0 3 9
        // 0 0 0 0 0 5
        // 0 0 0 0 0 0
        // 0,2->9
        // 1,3->10
        // 2,4->9
        // 9
    }
}
