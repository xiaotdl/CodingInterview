import java.util.ArrayList;

public class Triangle {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */

    // V1, O(n^2)
    // DP(matrix, bottom up)
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int n = triangle.size();
        int[][] sum = new int[n][n];

        for (int i = 0; i < n; i++) {
            sum[n - 1][i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        return sum[0][0];
    }

    // V2, O(2^n)
    // DFS(traverse)
    private int minSum = Integer.MAX_VALUE;
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // int minSum = Integer.MAX_VALUE;
        int n = triangle.size();
        traverse(0, 0, 0, triangle);
        return minSum;
    }
    private void traverse(int x, int y, int sum, ArrayList<ArrayList<Integer>> triangle) {
        if (x == triangle.size()) {
            minSum = Math.min(minSum, sum);
            return;
        }

        traverse(x + 1, y, sum + triangle.get(x).get(y), triangle);
        traverse(x + 1, y + 1, sum + triangle.get(x).get(y), triangle);
    }

    // V3, O(2^n)
    // Devide&Conquer
    // Could be improved to O(n^2) using hash storing tmp val
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        return helper(0, 0, triangle);
    }
    private int helper(int x, int y, ArrayList<ArrayList<Integer>> triangle) {
        if (x == triangle.size()) {
            return 0;
        }

        return triangle.get(x).get(y) + Math.min(helper(x + 1, y, triangle), helper(x + 1, y + 1, triangle));
    }
}


