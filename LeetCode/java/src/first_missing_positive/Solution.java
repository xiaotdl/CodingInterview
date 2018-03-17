package first_missing_positive;

/**
 * Created by Xiaotian on 9/2/17.
 */
class Solution {
    // tag: array, swap
    // time: O(n)
    // space: O(1)
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while (i < A.length) {
            if (0 < A[i] && A[i] < A.length && A[A[i] - 1] != A[i]) {
                // store i+1 in nums[i]
                swap(A, i, A[i] - 1);
            }
            else i++;
        }

        i = 0;
        while (i < A.length && A[i] == i + 1) i++;
        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
