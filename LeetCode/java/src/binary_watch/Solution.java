package binary_watch;

import java.util.*;

/**
 * Created by Xiaotian on 10/21/17.
 */
public class Solution {
    // hour: 0-11
    // minute: 00-59
    // tag: bit
    // time: O(1)
    // space: O(1)
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (countOnes(h) + countOnes(m) == num) {
                    res.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return res;
    }

    private int countOnes(int n) {
        int cnt = 0;
        while (n > 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }
}
