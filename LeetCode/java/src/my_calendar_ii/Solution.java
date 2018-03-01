package my_calendar_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/1/18.
 */
public class Solution {
}
class MyCalendarTwo {
    TreeMap<Integer,Integer> m;
    public MyCalendarTwo() {
        m = new TreeMap<Integer,Integer>(); // time2delta
    }

    public boolean book(int start, int end) {
        m.put(start, m.getOrDefault(start, 0) + 1);
        m.put(end, m.getOrDefault(end, 0) - 1);
        int bookedCnt = 0;
        for (Map.Entry<Integer,Integer> e : m.entrySet()) {
            int delta = e.getValue();
            bookedCnt += delta;
            if (bookedCnt == 3) {
                m.put(start, m.get(start) - 1);
                m.put(end, m.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}