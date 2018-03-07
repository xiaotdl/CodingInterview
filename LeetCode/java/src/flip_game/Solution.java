package flip_game;

import java.util.*;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) return res;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return res;
    }
}

class SolutionII {
    // Same as Solution, without using loop
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        char[] state = s.toCharArray();
        dfs(state, 1, res);
        return res;
    }

    private void dfs(char[] state, int i, List<String> res) {
        if (i >= state.length) return;
        if (state[i] == '+' && state[i - 1] == '+') {
            state[i - 1] = '-';
            state[i] = '-';
            res.add(new String(state));
            state[i - 1] = '+';
            state[i] = '+';
        }
        dfs(state, i + 1, res);
    }
}
