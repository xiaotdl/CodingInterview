package my_calendar_iii;

import java.util.*;

/**
 * Created by Xiaotian on 3/1/18.
 */
public class Solution {
}
class MyCalendarThree {
    TreeMap<Integer, Integer> m;

    public MyCalendarThree() {
        m = new TreeMap<Integer, Integer>(); // time2delta
    }

    public int book(int start, int end) {
        m.put(start, m.getOrDefault(start, 0) + 1);
        m.put(end, m.getOrDefault(end, 0) - 1);
        int bookedCnt = 0;
        int maxCnt = 0;
        for (Map.Entry<Integer,Integer> e : m.entrySet()) {
            int delta = e.getValue();
            bookedCnt += delta;
            if (bookedCnt > maxCnt) {
                maxCnt = bookedCnt;
            }
        }
        return maxCnt;
    }
}
