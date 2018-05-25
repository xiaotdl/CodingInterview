package domino_and_tromino_tiling;

/**
 * Created by Xiaotian on 2/24/18.
 */
class Solution {
    // Wrong for case N=4, where there are 2 L and 1 XX
    // AXXB
    // AABB
    int cnt;
    int[] shapeVal;
    public int numTilings(int N) {
        cnt = 0;
        // 0 1  2  3  4  5  6  7
        //   X XX    X  XX XX  X
        //   X    XX XX  X X  XX
        // 0 0 -1  1 -2  2 -3  3
        shapeVal = new int[]{0, 0, -1, 1, -2, 2, -3, 3};
        dfs(Integer.MIN_VALUE, N, "");
        return cnt;
    }

    private void dfs(int prevShapeVal, int N, String lvl) {
        System.out.println(lvl + "prev: " + prevShapeVal + " N: " + N);
        if (N < 0) return;
        if (N == 0) {
            cnt++;
            System.out.println("cnt: " + cnt);
            return;
        }

        if (prevShapeVal == Integer.MIN_VALUE || prevShapeVal == 0) {
            dfs(0, N - 1, lvl+"  "); // 0
            dfs(shapeVal[2], N, lvl+"  "); // 2
            dfs(shapeVal[4], N, lvl+"  "); // 4
            dfs(shapeVal[6], N, lvl+"  "); // 6
        }
        else if (prevShapeVal == -1) {
            dfs(0, N - 2, lvl+"  ");
        }
        else if (prevShapeVal == -2) {
            dfs(0, N - 3, lvl+"  ");
        }
        else if (prevShapeVal == -3) {
            dfs(0, N - 3, lvl+"  ");
        }
    }
}

class SolutionII {
    // Ref: https://leetcode.com/articles/domino-and-tromino-tiling/
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int numTilings(int N) {
        int MOD = 1_000_000_007;
        long[] prev = new long[]{1, 0, 0, 0};
        for (int i = 0; i < N; ++i) {
            long[] curr = new long[4];
            curr[0b00] = (prev[0b00] + prev[0b11]) % MOD;
            curr[0b01] = (prev[0b00] + prev[0b10]) % MOD;
            curr[0b10] = (prev[0b00] + prev[0b01]) % MOD;
            curr[0b11] = (prev[0b00] + prev[0b01] + prev[0b10]) % MOD;
            prev = curr;
        }
        return (int) prev[0];
    }
}

// https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1+dpn-3
