import java.util.Set;

public class WordBreak {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */

    // V1, O(nl), l = longest word length
    // DP(sequence)
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int maxWordLength = getMaxWordLength(dict);
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            canBreak[i] = false;
            for (int j = 1; j <= maxWordLength && j <= i; j++) {
                if (!canBreak[i - j]) {
                    continue;
                }

                String word = s.substring(i- j, i);
                if (dict.contains(word)) {
                    canBreak[i] = true;
                    break;
                }
            }
        }

        return canBreak[s.length()];
    }
    private int getMaxWordLength(Set<String> dict) {
        int maxWordLength = 0;
        for (String word : dict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }
        return maxWordLength;
    }
}

