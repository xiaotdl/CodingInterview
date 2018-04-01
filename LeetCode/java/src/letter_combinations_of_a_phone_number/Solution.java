package letter_combinations_of_a_phone_number;

import java.util.*;

/**
 * Created by Xiaotian on 5/16/16.
 */
public class Solution {
    // tag: str, dfs, backtracking, recursion, combinations
    // time: O(3^n), each digit averages to three chars.
    // space: O(n), n level depth stack space
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        Map<Character, ArrayList<Character>> m = new HashMap<>();
        m.put('0', new ArrayList<Character>());
        m.put('1', new ArrayList<Character>());
        m.put('2', new ArrayList<Character>(Arrays.asList('a', 'b', 'c')));
        m.put('3', new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));
        m.put('4', new ArrayList<Character>(Arrays.asList('g', 'h', 'i')));
        m.put('5', new ArrayList<Character>(Arrays.asList('j', 'k', 'l')));
        m.put('6', new ArrayList<Character>(Arrays.asList('m', 'n', 'o')));
        m.put('7', new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
        m.put('8', new ArrayList<Character>(Arrays.asList('t', 'u', 'v')));
        m.put('9', new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));

        dfs(m, digits, 0, new StringBuffer(), res);

        return res;
    }

    private void dfs(Map<Character, ArrayList<Character>> m, String digits, int pos, StringBuffer tmpRes, List<String> res) {
        if (pos == digits.length()) {
            res.add(tmpRes.toString());
            return;
        }

        ArrayList<Character> letters = m.get(digits.charAt(pos));
        for (int i = 0; i < letters.size(); i++) {
            tmpRes.append(letters.get(i));
            dfs(m, digits, pos + 1, tmpRes, res);
            tmpRes.deleteCharAt(tmpRes.length() - 1);
        }
    }

}

class SolutionII {
    // 用String[]来存mapping
    // String[] dict = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        String[] charMap = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; // digit2char
        dfs(charMap, digits, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String[] charMap, String digits, int pos, StringBuilder path, List<String> res) {
        if (pos == digits.length()) {
            res.add(path.toString());
            return;
        }

        int digit = digits.charAt(pos) - '0';
        for (char c : charMap[digit].toCharArray()) {
            path.append(c);
            dfs(charMap, digits, pos + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
