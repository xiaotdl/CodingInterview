package my_calendar_i;

import java.util.*;

/**
 * Created by Xiaotian on 2/25/18.
 */
public class Solution {
}

class Time implements Comparable<Time> {
    int s;
    int e;
    Time(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Time other) {
        if (this.s != other.s) return this.s - other.s;
        return this.e - other.e;
    }
}

class MyCalendar {
    TreeSet<Time> ts;
    public MyCalendar() {
        ts = new TreeSet<>();
    }

    // Add: O(logn), Search: O(logn)
    public boolean book(int s, int e) {
        Time T = new Time(s, e);
        Time prevT = ts.floor(T);
        Time nextT = ts.ceiling(T);
        if (prevT != null && prevT.e > s) {
            return false;
        }
        if (nextT != null && nextT.s < e) {
            return false;
        }
        ts.add(T);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
