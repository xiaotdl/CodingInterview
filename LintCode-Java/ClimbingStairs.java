public class ClimbingStairs {
    /**
     * @param n: An integer
     * @return: An integer
     */

    // V1, O(n)
    // DP(sequence)
    // Fibonacci Sequence
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int lastlast = 1, last = 2;
        int now = 0;
        for (int i = 2; i < n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }

        return now;
    }
}

