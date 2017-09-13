package teemo_attacking;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class Solution {
    // sliding window
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;

        int totalDuration = 0;
        int start = timeSeries[0];
        int end = start + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            int now = timeSeries[i];
            if (end < now) {
                totalDuration += end - start;
                start = now;
                end = start + duration;
            }
            else {
                end = now + duration;
            }
        }
        totalDuration += end - start;
        return totalDuration;
    }
}
