package employee_free_time;

import java.util.*;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Solution {
    // tag: sweep line
    // time: O(nlogn), where n is the number of intervals across all employees.
    // space: O(n)
    private final static int START = 0;
    private final static int END = 1;
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> events = new ArrayList<>();
        for (List<Interval> employeeWorkingIntervals : schedule) {
            for (Interval interval : employeeWorkingIntervals) {
                events.add(new int[]{interval.start, START});
                events.add(new int[]{interval.end, END});
            }
        }

        Collections.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2) { // int[] e: {time, type}
                if (e1[0] != e2[0]) return e1[0] - e2[0];
                return e1[1] - e2[1]; // add start first
            }
        });

        List<Interval> res = new ArrayList<>();
        int prev = -1;
        int cnt = 0; // count of current working employees
        for (int[] e : events) {
            int time = e[0];
            int type = e[1];
            if (cnt == 0 && prev >= 0) {
                res.add(new Interval(prev, time));
            }
            cnt += (type == START ? 1 : -1);
            prev = time;
        }
        return res;
    }
}
