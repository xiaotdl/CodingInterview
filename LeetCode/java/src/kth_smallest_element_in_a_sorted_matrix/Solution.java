package kth_smallest_element_in_a_sorted_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Xiaotian on 10/31/16.
 */
public class Solution {
    // tag: heap
    // time: O(klogk)
    // space: O(k)
    class Element {
        int x, y, val;
        Element(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    /*
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        PriorityQueue<Element> minHeap = new PriorityQueue<>(k,
                new Comparator<Element>(){
                    @Override
                    public int compare(Element e1, Element e2) {
                        return e1.val - e2.val;
                    }
                }
        );

        minHeap.offer(new Element(0, 0, matrix[0][0]));
        // take out k-1 elments
        while (!minHeap.isEmpty() && k - 1 > 0) {
            Element curr = minHeap.poll();
            int x = curr.x;
            int y = curr.y;
            if (y < n - 1) {
                minHeap.offer(new Element(x, y + 1, matrix[x][y + 1]));
            }
            if (y == 0 && x < m - 1) {
                minHeap.offer(new Element(x + 1, y, matrix[x + 1][y]));
            }
            k--;
        }
        return minHeap.peek().val;
    }
}
