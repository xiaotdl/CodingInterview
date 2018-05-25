package interval_summary;

import java.util.*;

/**
 * Created by Xiaotian on 4/1/18.
 */
public class Test {
    public static void main(String[] args) {
        List<Interval> intervals;
        intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 10));
        intervals.add(new Interval(10, 11));
        intervals.add(new Interval(12, 15));
        System.out.println(intervals);

        System.out.println(Arrays.toString(new Solution().printIntervals(intervals)));

        System.out.println("Union"); // 并集/everything
        System.out.println(new Solution().union(intervals));

        System.out.println("Complement"); // 补集/everything else
        System.out.println(new Solution().complement(intervals));

        System.out.println("Intersection"); // 交集/duplicates
        System.out.println(new Solution().intersection(intervals));

        System.out.println("Symmetric Difference"); // 对称差集/unique
        System.out.println(new Solution().symmetricDiff(intervals));
    }
}


class Interval {
    int start;
    int end;
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString(){
        return String.format("(%d, %d)", start, end);
    }
}

class Solution {
    public int[] printIntervals(List<Interval> intervals) {
        TreeMap<Integer, Integer> m = new TreeMap<>(); // time2delta
        for (Interval interval : intervals) {
            m.put(interval.start, m.getOrDefault(interval.start, 0) + 1);
            m.put(interval.end + 1, m.getOrDefault(interval.end + 1, 0) - 1);
        }
        int[] res = new int[m.lastKey()];
        int cnt = 0;
        for (int i = 0; i < res.length; i++) {
            if (m.containsKey(i)) cnt += m.get(i);
            res[i] = cnt;
        }
        return res;
    }

    public List<Interval> union(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;

        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start != i2.start) return i1.start - i2.start;
                return i1.end - i2.end;
            }
        });

        for (Interval i : intervals) {
            if (res.isEmpty() || res.get(res.size() - 1).end < i.start) {
                res.add(new Interval(i.start, i.end));
            }
            else {
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, i.end);
            }
        }
        return res;
    }

    public List<Interval> complement(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        List<Interval> union_intervals = this.union(intervals);
        int prev = Integer.MIN_VALUE;
        for (Interval i : union_intervals) {
            if (prev != i.start) {
                res.add(new Interval(prev, i.start));
            }
            prev = i.end;
        }
        res.add(new Interval(prev, Integer.MAX_VALUE));
        return res;
    }

    public List<Interval> intersection(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;

        TreeMap<Integer, Integer> m = new TreeMap<>(); // time2delta
        for (Interval interval : intervals) {
            m.put(interval.start, m.getOrDefault(interval.start, 0) + 1);
            m.put(interval.end, m.getOrDefault(interval.end, 0) - 1);
        }
//        System.out.println(m);

        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i : m.keySet()) {
            cnt += m.get(i);
//            System.out.println(i + ": " + cnt);
            if (cnt >= 2) {
                if (stack.isEmpty()) {
                    stack.push(i);
                }
            }
            else {
                if (!stack.isEmpty()) {
                    res.add(new Interval(stack.pop(), i));
                }
            }
        }
        return res;
    }

    public List<Interval> symmetricDiff(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;

        TreeMap<Integer, Integer> m = new TreeMap<>(); // time2delta
        for (Interval interval : intervals) {
            m.put(interval.start, m.getOrDefault(interval.start, 0) + 1);
            m.put(interval.end, m.getOrDefault(interval.end, 0) - 1);
        }
//        System.out.println(m);

        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i : m.keySet()) {
            cnt += m.get(i);
//            System.out.println(i + ": " + cnt);
            if (cnt == 1) {
                if (stack.isEmpty()) {
                    stack.push(i);
                }
            }
            else {
                if (!stack.isEmpty()) {
                    res.add(new Interval(stack.pop(), i));
                }
            }
        }
        return res;
    }
}
