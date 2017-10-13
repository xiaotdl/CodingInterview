package sticker_to_spell_word;

import java.util.*;

/**
 * Created by Xiaotian on 10/11/17.
 */
public class Solution {
    // bottom-up dp, similar to Stairs Climbing Puzzle
    // tag: dp
    // time: O(N*m*n), N: 2^len(target), m: len(stickers), n: len(target)
    // space: O(N)
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int states = (int) Math.pow(2, n); // == 1 << n

        // dp[state]: minStickers to achieve a state, a state is a bitmap that stores subset mapping to chars in target string
        int[] dp = new int[states];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int currState = 0; currState < states; currState++) {
            if (dp[currState] == Integer.MAX_VALUE) continue;

            for (String sticker : stickers) {
                int nextState = currState;
                for (int i = 0; i < sticker.length(); i++) {
                    for (int j = 0; j < target.length(); j++) {
                        if (sticker.charAt(i) == target.charAt(j)
                                && (nextState & (1 << j)) == 0) {
                            nextState |= 1 << j;
                            break;
                        }
                    }
                }
                dp[nextState] = Math.min(dp[nextState], dp[currState] + 1);
            }
        }
        return dp[states - 1] == Integer.MAX_VALUE ? -1 : dp[states - 1];
    }
}
