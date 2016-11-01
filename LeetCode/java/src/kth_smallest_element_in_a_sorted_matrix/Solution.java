package kth_smallest_element_in_a_sorted_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Xiaotian on 10/31/16.
 */
public class Solution {
    // tag: Heap
    // time: O(klogk)
    // space: O(k)
    class Element {
        int i;
        int j;
        int val;
        Element(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // min heap
        PriorityQueue<Element> pq = new PriorityQueue<>(k,
                new Comparator<Element>(){
                    @Override
                    public int compare(Element a, Element b) {
                        return a.val - b.val;
                    }
                }
        );

        pq.offer(new Element(0, 0, matrix[0][0]));
        int result = -1;
        while (!pq.isEmpty() && k-- > 0) {
            Element currElement = pq.poll();
            result = currElement.val;
            int i = currElement.i;
            int j = currElement.j;
            if (j + 1 < cols) {
                pq.offer(new Element(i, j + 1, matrix[i][j + 1]));
            }
            if (j == 0 && i + 1 < rows) {
                pq.offer(new Element(i + 1, j, matrix[i + 1][j]));
            }
        }

        return result;
    }
}
