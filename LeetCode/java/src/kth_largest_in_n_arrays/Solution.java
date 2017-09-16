package kth_largest_in_n_arrays;

import java.util.*;

/**
 * Created by Xiaotian on 9/16/17.
 */
public class Solution {
    // tag: Heap
    // time: O(m*nlogn+(m+k)logk)
    // space: O(k)
    class Element {
        int i, j, val;
        Element(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    /*
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        if (arrays == null || arrays.length == 0) return -1;

        PriorityQueue<Element> maxHeap = new PriorityQueue<>(k,
                new Comparator<Element>(){
                    @Override
                    public int compare(Element e1, Element e2) {
                        return e2.val - e1.val;
                    }
                }
        );

        int m = arrays.length;
        for (int i = 0; i < m; i++) {
            Arrays.sort(arrays[i]);

            if (arrays[i].length > 0) {
                int j = arrays[i].length - 1;
                maxHeap.offer(new Element(i, j, arrays[i][j]));
            }
        }
        // take out k-1 elments
        while (!maxHeap.isEmpty() && k - 1 > 0) {
            Element curr = maxHeap.poll();
            int i = curr.i;
            int j = curr.j;
            if (j > 0) {
                maxHeap.offer(new Element(i, j - 1, arrays[i][j - 1]));
            }
            k--;
        }
        return maxHeap.peek().val;
    }
};
