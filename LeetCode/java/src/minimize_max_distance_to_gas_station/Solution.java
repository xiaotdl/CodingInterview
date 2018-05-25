package minimize_max_distance_to_gas_station;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Interval {
    double l;
    double r;
    int insertedStationCnt;

    public Interval(double l, double r, int insertedStationCnt) {
        this.l = l;
        this.r = r;
        this.insertedStationCnt = insertedStationCnt;
    }

    double avgDistance() {
        return (r - l) / ((double) (insertedStationCnt + 1));
    }
}

class Solution {
    // Proportional Assignment + PriorityQueue Greedy for leftover  O(n log n)
    // credit: https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113630/Java-Proportional-Assignment-+-PriorityQueue-Greedy-for-leftover-O(n-log-n)-easy-to-understand
    // explaination:  we round them down to floor integer and use what we can. Then, later, we correct for this by using the priority queue / greedy assignment to the almost-finished intervals.
    // tag: heap
    // time: O(nlogn)
    // space: O(n)
    public double minmaxGasDist(int[] stations, int k) {
        Arrays.sort(stations);

        // max heap
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(
                new Comparator<Interval>(){
                    @Override
                    public int compare(Interval a, Interval b) {
                        double diff = a.avgDistance() - b.avgDistance();
                        if (diff == 0) return 0;
                        return diff > 0 ? -1 : 1;
                    }
                }
        );

        int fullLen = stations[stations.length - 1] - stations[0];
        int remaining = k;
        for (int i = 0; i < stations.length - 1; i++) {
            int insertedStationCnt = (int) (k * (((double) (stations[i + 1] - stations[i])) / fullLen)); // floor cnt, leftover cnt will be added later
            pq.offer(new Interval(stations[i], stations[i + 1], insertedStationCnt));
            remaining -= insertedStationCnt;
        }

        while (remaining > 0) {
            Interval interval = pq.poll();
            interval.insertedStationCnt++;
            pq.offer(interval);
            remaining--;
        }

        Interval max = pq.poll();
        return max.avgDistance();
    }
}
