public class Heapify {
    /**
     * @param A: Given an integer array
     * @return: void
     */

    // V1, O(nlogn)
    // PriorityQueue(Heap)
    public void heapify(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        }
    }
    private void siftdown(int[] A, int i) {
        while (i < A.length) {
            int min = i;
            if (2 * i + 1 < A.length && A[2 * i + 1] < A[min]) {
                min = 2 * i + 1;
            }
            if (2 * i + 2 < A.length && A[2 * i + 2] < A[min]) {
                min = 2 * i + 2;
            }

            if (min == i) {
                break;
            }

            int tmp = A[min];
            A[min] = A[i];
            A[i] = tmp;

            i = min;
        }
    }
}
