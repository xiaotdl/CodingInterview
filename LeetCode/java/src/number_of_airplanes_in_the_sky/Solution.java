package number_of_airplanes_in_the_sky;

import java.util.*;

/**
 * Created by Xiaotian on 9/26/17.
 */

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Solution {
    // tag: sweep line
    // time: O(nlogn)
    // space: O(n)
    class Event implements Comparable<Event> {
        final static int FLY = 0;
        final static int LAND = 1;
        int time;
        int action;

        public Event (int time, int action) {
            this.time = time;
            this.action = action;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) return this.time - other.time;
            return this.action - other.action;
        }
    }
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return 0;

        List<Event> events = new ArrayList<>(intervals.size()*2);
        TreeSet<Integer> times = new TreeSet<>();
        for (Interval interval : intervals) {
            events.add(new Event(interval.start, Event.FLY));
            events.add(new Event(interval.end, Event.LAND));
            times.add(interval.start);
            times.add(interval.end);
        }

        Collections.sort(events);

        // start sweeping line
        int i = 0;
        int currCnt = 0;
        int maxCnt = 0;
        while (!times.isEmpty()) {
            int currTime = times.pollFirst();
            while (i < events.size() && events.get(i).time == currTime) {
                Event e = events.get(i);
                if (e.action == Event.FLY) {
                    currCnt++;
                } else {
                    currCnt--;
                }
                i++;
            }
            maxCnt = Math.max(maxCnt, currCnt);
        }
        return maxCnt;
    }
}


