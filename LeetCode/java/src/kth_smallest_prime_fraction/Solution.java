package kth_smallest_prime_fraction;

import java.util.*;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // tag: heap
    // time: O(klogk)
    // space: O(k)
    public int[] kthSmallestPrimeFraction(int[] A, int k) {
        // min heap
        // x1/y1 vs x2/y2 is the same as: x1*y2 vs x2*y1
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){ // int[]: {i, j}
            @Override
            public int compare(int[] pair1, int[] pair2) {
                int x1 = A[pair1[0]], y1 = A[pair1[1]];
                int x2 = A[pair2[0]], y2 = A[pair2[1]];
                return x1*y2 - x2*y1;
            }
        });

        pq.offer(new int[]{0, A.length - 1});
        while (k - 1 > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int i = pair[0];
            int j = pair[1];
            if (j == A.length - 1 && i + 1 < j) pq.offer(new int[]{i + 1, j});
            if (i < j - 1) pq.offer(new int[]{i, j - 1});
            k--;
        }
        int[] kthPair = pq.poll(); // kth smallest pair
        return new int[]{A[kthPair[0]], A[kthPair[1]]};
    }
}
