package meeting_rooms_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/13/18.
 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Event {
    int time;
    int type; // 0: start, 1: end
    Event(int time, int type) {
        this.time = time;
        this.type = type;
    }
}

public class Solution {
    // tag: sweep line, discretization
    // time: O(nlogn)
    // space: O(1)
    public int minMeetingRooms(Interval[] intervals) {
        List<Event> events = new ArrayList<>();
        for (Interval i : intervals) {
            events.add(new Event(i.start, 0));
            events.add(new Event(i.end, 1));
        }

        Collections.sort(events, new Comparator<Event>(){
            @Override
            public int compare(Event e1, Event e2) {
                return e1.time - e2.time;
            }
        });

        int cnt = 0; // currMeetingRoomCnt
        int maxCnt = 0;
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.type == 0) cnt++;
            if (e.type == 1) cnt--;
            if (i + 1 < events.size() && events.get(i + 1).time == e.time) continue;
            // calculate max only once for events happening at the same time
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;
    }
}
