package kth_smallest_element_in_a_sorted_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Xiaotian on 10/31/16.
 */
class Solution {
    // tag: heap
    // time: O(klogk)
    // space: O(k)
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2) {
                int i1 = pair1[0], j1 = pair1[1];
                int i2 = pair2[0], j2 = pair2[1];
                return matrix[i1][j1] - matrix[i2][j2];
            }
        });

        pq.offer(new int[]{0, 0});
        while (k - 1 > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int i = pair[0];
            int j = pair[1];
            if (j + 1 < n) pq.offer(new int[]{i, j + 1});
            if (j == 0 && i + 1 < m) pq.offer(new int[]{i + 1, j});
            k--;
        }
        int[] kthPair = pq.poll();
        return matrix[kthPair[0]][kthPair[1]];
    }
}
