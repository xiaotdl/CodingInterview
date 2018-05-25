package can_i_win;

import java.util.*;

/**
 * Created by Xiaotian on 4/10/18.
 */
class Solution {
    // O(n*2^n)&O(n) dfs + memo, not a good to example of transform from recursion to loop(DP), as the state(2^n) is huge
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        int state = ~0 << maxChoosableInteger;
        Map<Integer, Boolean> memo = new HashMap<>();
        return search(state, maxChoosableInteger, desiredTotal, memo);
    }

    private boolean search(int state, int maxNum, int leftTotal, Map<Integer, Boolean> memo) {
        if (leftTotal <= 0) return false;
        if (memo.containsKey(state)) return memo.get(state);

        for (int i = 1; i <= maxNum; i++) {
            int mask = 1 << (i - 1);
            if ((state & mask) != 0) continue; // visited
            state |= mask;
            if (!search(state, maxNum, leftTotal - i, memo)) { // if opponent loses, I win
                state ^= mask;
                memo.put(state, true);
                return true;
            }
            state ^= mask;
        }
        memo.put(state, false);
        return false;
    }
}
