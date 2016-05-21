package letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Xiaotian on 5/16/16.
 */
public class Solution {
    // tag: string, backtracking, recursion, combinations
    // time: O(3^n), each digit averages to three chars.
    // space: O(n), n level depth stack space
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        HashMap<Character, ArrayList<Character>> hm = new HashMap<Character, ArrayList<Character>>();
        hm.put('0', new ArrayList<Character>());
        hm.put('1', new ArrayList<Character>());
        hm.put('2', new ArrayList<Character>(Arrays.asList('a', 'b', 'c')));
        hm.put('3', new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));
        hm.put('4', new ArrayList<Character>(Arrays.asList('g', 'h', 'i')));
        hm.put('5', new ArrayList<Character>(Arrays.asList('j', 'k', 'l')));
        hm.put('6', new ArrayList<Character>(Arrays.asList('m', 'n', 'o')));
        hm.put('7', new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
        hm.put('8', new ArrayList<Character>(Arrays.asList('t', 'u', 'v')));
        hm.put('9', new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));

        helper(hm, digits, 0, new StringBuilder(), result);

        return result;
    }

    private void helper(HashMap<Character, ArrayList<Character>> hm, String digits, int pos, StringBuilder tmpResult, List<String> result) {
        if (pos == digits.length()) {
            result.add(tmpResult.toString());
            return;
        }

        ArrayList<Character> chars = hm.get(digits.charAt(pos));
        System.out.println(chars.toString());
        for (int i = 0; i < chars.size(); i++) {
            tmpResult.append(chars.get(i));
            helper(hm, digits, pos + 1, tmpResult, result);
            tmpResult.deleteCharAt(tmpResult.length() - 1);
        }
    }
}
