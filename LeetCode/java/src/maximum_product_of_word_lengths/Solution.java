package maximum_product_of_word_lengths;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // use a int(32bit) to store if a word has 26 alphabets.
    // tag: bit
    // time: O(n^2), n: len(words
    // space: O(n)
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;

        int n = words.length;
        int[] alphabets = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                alphabets[i] |= (1 << (words[i].charAt(j) - 'a'));
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((alphabets[i] & alphabets[j]) > 0) continue;

                max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }
}
