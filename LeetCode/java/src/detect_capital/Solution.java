package detect_capital;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean detectCapitalUse(String word) {
        return isAllCaps(word)
            || isNoCaps(word)
            || isOnlyFirstCap(word);
    }

    private boolean isAllCaps(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isUpperCase(c)) return false;
        }
        return true;
    }

    private boolean isNoCaps(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) continue;
            return false;
        }
        return true;
    }

    private boolean isOnlyFirstCap(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (i == 0 && Character.isUpperCase(c)) continue;
            else if (i != 0 && !Character.isUpperCase(c)) continue;
            return false;
        }
        return true;
    }
}
