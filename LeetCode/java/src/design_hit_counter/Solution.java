package design_hit_counter;

/**
 * Created by Xiaotian on 4/16/18.
 */
public class Solution {
}

class HitCounter {
    // use second buckets to store hit counts
    // tag: circular array
    // time:
    //   hit: O(1)
    //   getHits: O(CAPACITY)
    // space: O(CAPACITY)

    int CAPACITY = 5 * 60; // seconds
    int[] timestamps;
    int[] hitCnts;

    public HitCounter() {
        timestamps = new int[CAPACITY]; // timestamp bucket
        hitCnts = new int[CAPACITY]; // hitCnt bucket
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int idx = timestamp % 300; // bucketIdx
        if (timestamps[idx] != timestamp) { // old bucket with expired data, to be reset
            timestamps[idx] = timestamp;
            hitCnts[idx] = 1;
            return;
        }
        hitCnts[idx]++;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int totalHitCnt = 0;
        for (int i = 0; i < CAPACITY; i++) {
            if (timestamp - timestamps[i] >= 300) continue; // ignore expired data
            totalHitCnt += hitCnts[i];
        }
        return totalHitCnt;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
