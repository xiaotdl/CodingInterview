import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */

    // V1, O(n*2^n)
    // Subsets, Recursion
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s == null) {
            return result;
        }

        ArrayList<String> list = new ArrayList<String>();
        helper(s, list, 0, result);

        return result;
    }
    private void helper(String s, ArrayList<String> list, int pos, List<List<String>> result) {
        if (pos == s.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            String prefix = s.substring(pos, i + 1);
            if (!isPalindrome(prefix)) {
                continue;
            }

            list.add(prefix);
            helper(s, list, i + 1, result);
            list.remove(list.size() - 1);
        }
    }
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

