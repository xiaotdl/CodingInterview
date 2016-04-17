import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    /**
     * @param nums: A list of integers
     * @return an integer
     */

    // V1, O(n)
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int max = 0;
        Set<Integer> set = new HashSet<Integer>();
        for (int i : num) {
            set.add(i);
        }

        for (int i : num) {
            int left = i - 1;
            int right = i + 1;
            int count = 1;

            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }

            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
