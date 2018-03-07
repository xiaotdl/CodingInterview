package flip_game_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: dfs
    // time: O(n!)
    // space: O(1)
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) return false;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(t)) return true;
            }
        }

        return false;
    }
}

class SolutionII {
    // tag: dfs, memo
    // time: O(2^n)
    // space: O(n)
    public boolean canWin(String s) {
        char[] state = s.toCharArray();
        return flip(state, 1);
    }

    private boolean flip(char[] state, int lvl) {
        for (int i = 1; i < state.length; i++) {
            if (state[i] == '+' && state[i - 1] == '+') {
                state[i - 1] = '-';
                state[i] = '-';
                if (!flip(state, lvl + 1)) {
                    // with dfs+memo, we need to recover the state so as to search in next iteration
                    state[i - 1] = '+';
                    state[i] = '+';
                    return true;
                }
                state[i - 1] = '+';
                state[i] = '+';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canWin("++++"));
    }
}
