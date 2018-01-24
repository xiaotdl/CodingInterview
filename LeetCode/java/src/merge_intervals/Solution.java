package merge_intervals;

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

public class Solution {
    // tag: array
    // time: O(nlogn)
    // space: O(n)

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        List<Interval> res = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            // overlapping: record max end
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            }
            // non-overlapping: disjoint intervals
            else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        res.add(new Interval(start, end));
        return res;
    }
}

class SolutionII {
    // tag: array
    // time: O(nlogn)
    // space: O(n)
    /*
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> res = new LinkedList<>();

        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        Interval prev = null;
        for (Interval curr : intervals) {
            if (prev == null || prev.end < curr.start) {
                res.add(curr);
                prev = curr;
            }
            else {
                prev.end = Math.max(prev.end, curr.end); // modify the interval in place
            }
        }

        return res;
    }
}
