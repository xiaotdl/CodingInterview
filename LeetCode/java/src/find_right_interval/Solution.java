package find_right_interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Xiaotian on 11/1/16.
 */
public class Solution {
    // tag: brutal force
    // time: O(n^2)
    // space: O(1)
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public int[] findRightInterval1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }

        int[] result = new int[intervals.length];

        Arrays.fill(result, -1);

        for (int i = 0; i < intervals.length; i++) {
            int minEndToStartDistance = Integer.MAX_VALUE;
            for (int j = 0; j < intervals.length; j++) {
                if (i == j) continue;
                int endToStartDistance = intervals[j].start - intervals[i].end;
                if (endToStartDistance >= 0 && endToStartDistance < minEndToStartDistance) {
                    result[i] = j;
                    minEndToStartDistance = endToStartDistance;
                }
            }
        }

        return result;
    }
    // tag: binary search
    // time: O(nlogn), binary search (logn) first larger obj for n times
    // space: O(n), a hash to save index mapping (n), a cloned array for sorting (n)
    public int[] findRightInterval2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }

        Interval[] intervalsSorted = intervals.clone();
        // Save original index before sorting
        HashMap<Interval, Integer> indexMapping = new HashMap<>();
        for (int i = 0; i < intervalsSorted.length; i++) {
            indexMapping.put(intervalsSorted[i], i);
        }
        Arrays.sort(
                intervalsSorted,
                new Comparator<Interval>(){
                    @Override
                    public int compare(Interval i1, Interval i2) {
                        return i1.start - i2.start;
                    }
                }
        );

        int[] result = new int[intervals.length];

        Arrays.fill(result, -1);

        for (int i = 0; i < intervals.length; i++) {
            int j = binarySearchFirstLarger(intervalsSorted, intervals[i]);
            if (j != -1) {
                result[i] = indexMapping.get(intervalsSorted[j]);
            }
        }

        return result;
    }

    int binarySearchFirstLarger(Interval[] intervals, Interval targetInterval) {
        int start = 0;
        int end = intervals.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (intervals[mid].start >= targetInterval.end) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (intervals[start].start >= targetInterval.end) {
            return start;
        }
        if (intervals[end].start >= targetInterval.end) {
            return end;
        }
        return -1;
    }
}
