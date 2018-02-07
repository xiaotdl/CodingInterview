package heapify;

/**
 * Created by Xiaotian on 2/6/18.
 */
public class Solution {
    // tag: heap
    // time: O(nlogn)
    // space: O(1)
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    private void siftup(int[] A, int k) {
        while (k != 0) {
            int parent = (k - 1) / 2; // parent to both 2k+1 and 2k+2
            if (A[k] > A[parent]) {
                break;
            }
            int temp = A[k];
            A[k] = A[parent];
            A[parent] = temp;

            k = parent;
        }
    }

    public void heapify(int[] A) {
        for (int i = 0; i < A.length; i++) {
            siftup(A, i);
        }
    }
}

class SolutionII {
    // siftdown is prefered over siftup
    // ref: https://aaronice.gitbooks.io/lintcode/content/data_structure/heapify.html
    // tag: heap
    // time: O(n)
    // space: O(1)
    /**
     * @param A: Given an integer array
     * @return: void
     */
    private void siftdown(int[] A, int k) {
        while (k < A.length) {
            int min = k; // min among A[k], A[2k+1], A[2k+2]
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[min]) {
                min = k * 2 + 1;
            }
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[min]) {
                min = k * 2 + 2;
            }
            if (min == k) {
                break;
            }
            int temp = A[min];
            A[min] = A[k];
            A[k] = temp;

            k = min;
        }
    }

    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        }
    }
}
