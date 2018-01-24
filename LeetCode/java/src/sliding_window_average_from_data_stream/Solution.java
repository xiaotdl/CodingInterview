package sliding_window_average_from_data_stream;

import java.util.*;

/**
 * Created by Xiaotian on 1/20/18.
 */
public class Solution {
}

class MovingAverage {
    // tag: queue

    Queue<Integer> q;
    int qMaxSize;
    double sum;

    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        q = new LinkedList<>();
        this.qMaxSize = size;
    }

    // time: O(n)
    // space: O(1)
    /*
     * @param val: An integer
     * @return:
     */
    public double next(int val) {
        if (q.size() == qMaxSize) {
            sum -= q.poll();
        }
        q.offer(val);
        sum += val;
        return sum / q.size();
    }
}
