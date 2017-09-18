package kth_smallest_sum_in_two_sorted_arrays;

import java.util.*;

/**
 * Created by Xiaotian on 9/17/17.
 */
public class Solution {
    // Space can be improved to O(k) if we replace isVisited[][] with visitedPairs hashset,
    // which needs to implement compareTo/equals method in Pair Class
    // tag: heap
    // time: O(k)
    // space: O(k+mn)
    class Pair {
        int x, y, sum;
        Pair(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }

    /*
     * @param A: an integer arrays sorted in ascending order
     * @param B: an integer arrays sorted in ascending order
     * @param k: An integer
     * @return: An integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        boolean[][] isVisited = new boolean[A.length][B.length];

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k,
                new Comparator<Pair>(){
                    @Override
                    public int compare(Pair a, Pair b) {
                        return a.sum - b.sum;
                    }
                }
        );

        minHeap.add(new Pair(0, 0, A[0] + B[0]));
        for (int i = 0; i < k - 1; i++) {
            Pair curr = minHeap.poll();
            for (int j = 0; j < 2; j++) {
                int nextX = curr.x + dx[j];
                int nextY = curr.y + dy[j];
                if (nextX < A.length && nextY < B.length && !isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    int sum = A[nextX] + B[nextY];
                    Pair nextPair = new Pair(nextX, nextY, sum);
                    minHeap.add(nextPair);
                }
            }
        }
        return minHeap.peek().sum;
    }
};
