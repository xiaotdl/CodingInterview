public class InterleavingPostiveAndNegativeNumbers {
    /**
     * @param A: An integer array.
     * @return: void
     */

    // V1. O(n), O(1)
    // Two pointers
    public void rerange(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        int posNum = 0, negNum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                posNum++;
            } else {
                negNum++;
            }
        }

        int posInd, negInd;
        if (posNum > negNum) {
            posInd = 0; negInd = 1;
        } else {
            posInd = 1; negInd = 0;
        }

        while (negInd < A.length && posInd < A.length) {
            while (negInd < A.length && A[negInd] < 0) {
                negInd += 2;
            }
            while (posInd < A.length && A[posInd] > 0) {
                posInd += 2;
            }
            if (posInd < A.length && negInd < A.length) {
                swap(A, posInd, negInd);
                posInd += 2;
                negInd += 2;
            }
        }

        return;
    }

    void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}

