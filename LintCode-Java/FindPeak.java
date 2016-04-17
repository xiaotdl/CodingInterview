public class FindPeak {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */

    // V1, O(logn)
    // Iteration, Binary Search
    public static int findPeak(int[] A) {
        if (A == null || A.length <= 2) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid - 1] < A[mid] && A[mid] < A[mid + 1]) {
                start = mid;
            } else if (A[mid - 1] > A[mid] && A[mid] > A[mid + 1]) {
                end = mid;
            } else {
                end = mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 5, 7, 6};
        System.out.println(findPeak(arr));
    }
}
