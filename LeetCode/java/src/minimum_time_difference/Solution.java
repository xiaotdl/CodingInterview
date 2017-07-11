package minimum_time_difference;

import java.util.*;

/**
 * Created by Xiaotian on 7/10/17.
 */
public class Solution {
    // tag: str
    // time: O(nlogn)
    // space: O(n)
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() <= 1) return 0;

        List<Integer> time = new ArrayList<>();
        for (int i = 0; i < timePoints.size(); i++) {
            time.add(getMins(timePoints.get(i)));
        }

//        Collections.sort(time, (Integer a, Integer b) -> a - b); // java8 support lambda
        Collections.sort(time, new Comparator<Integer>() {
            @Override
            public int compare(Integer min1, Integer min2) {
                return min1.compareTo(min2);
            }
        });

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < time.size(); i++) {
            min = Math.min(min, time.get(i) - time.get(i - 1));
        }
        int headTailDiff = time.get(0) + (24 * 60 - time.get(time.size() - 1));
        return Math.min(min, headTailDiff);
    }

    private int getMins(String timePoint) {
        Integer hour = Integer.valueOf(timePoint.substring(0, 2));
        Integer min = Integer.valueOf(timePoint.substring(3));
        return 60 * hour + min;
    }
}
