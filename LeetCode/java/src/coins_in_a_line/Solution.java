package coins_in_a_line;

/**
 * Created by Xiaotian on 10/1/17.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(n)
    /*
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // dp[i]: when there is i coins, first win or lose
        // 0: not visited, 1: first win, 2: first lose
        boolean[] dp = new boolean[n + 1];
        boolean[] isVisited = new boolean[n + 1];

        return memSearch(dp, isVisited, n);
    }

    private boolean memSearch(boolean[] dp, boolean[] isVisited, int n) {
        if (isVisited[n]) {
            return dp[n];
        }
        isVisited[n] = true;

        if (n == 0) {
            dp[n] = false;
        } else if (n == 1) {
            dp[n] = true;
        } else if (n == 2) {
            dp[n] = true;
        } else if (n == 3) {
            dp[n] = false;
        } else {
            // take 1 coin
            if ((memSearch(dp, isVisited, n - 2) && memSearch(dp, isVisited, n - 3))
            // take 2 coins
             || (memSearch(dp, isVisited, n - 3) && memSearch(dp, isVisited, n - 4))) {
                dp[n] = true;
            } else {
                dp[n] = false;
            }
        }

        return dp[n];
    }
}
