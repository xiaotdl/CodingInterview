package shortest_word_distance;

/**
 * Created by Xiaotian on 3/2/18.
 */
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public int shortestDistance(String[] words, String word1, String word2) {
        Integer p = null;
        Integer q = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) p = i;
            if (word.equals(word2)) q = i;
            if (p != null && q != null) {
                min = Math.min(min, Math.abs(p - q));
            }
        }
        return min;
    }
}
