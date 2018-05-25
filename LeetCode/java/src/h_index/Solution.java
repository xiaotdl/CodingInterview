package h_index;

import java.util.*;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // tag: sort
    // time: O(nlogn)
    // space: O(1)
    public int hIndex(int[] citations) {
        if (null == citations || citations.length == 0) return 0;

        Arrays.sort(citations);
        int max = 0; // maxH
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length - i) {
                max = Math.max(max, citations.length - i);
            }
            else {
                max = Math.max(max, citations[i]);
            }
        }
        return max;
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/h-index-ii/discuss/121442/C:-Intuitive-Solution-with-Explanation-(Accepted)
    // In the 1st pass, we can build the freq array with the number of papers in each citation bucket.
    // In the 2nd pass, we iterate from tail to head, add all the citations backwards until freqCount >= i, then return i.
    // tag: bucket
    // time: O(n)
    // space: O(n)
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        int[] freq = new int[citations.length + 1]; // frequency bucket
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length) {
                freq[citations.length]++;
            }
            else {
                freq[citations[i]]++;
            }
        }

        int freqCnt = 0;
        for (int i = citations.length; i > 0; i--) {
            freqCnt += freq[i];
            if (freqCnt >= i) return i;
        }
        return 0;
    }
}
