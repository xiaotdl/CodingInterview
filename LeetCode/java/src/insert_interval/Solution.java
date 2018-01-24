package insert_interval;

import java.util.*;

/**
 * Created by Xiaotian on 9/2/17.
 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Solution {
    // tag: array
    // time: O(n)
    // space: O(n)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end)
            );
            i++;
        }
        res.add(newInterval);

        while (i < intervals.size()) {
            res.add(intervals.get(i++));
        }

        return res;
    }
}

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


class SolutionII {
    // tag: array
    // time: O(n)
    // space: O(n)
    /*
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).start < newInterval.start) i++;
        intervals.add(i, newInterval);

        Interval prev = null;
        for (Interval curr : intervals) {
            if (prev == null || prev.end < curr.start) {
                result.add(curr);
                prev = curr;
            }
            else {
                prev.end = Math.max(prev.end, curr.end);
            }
        }

        return result;
    }
}
