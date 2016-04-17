public class SearchRotatedSortedArray {
    /**
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */

    // V1, O(logn)
    // Iteration, Binary Search
    public static int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[mid] >= A[start]) {
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 0, 1, 2, 3, 4, 5};
        System.out.println(search(arr, 3));
        System.out.println(search(arr, 7));
        System.out.println(search(arr, 9));
        System.out.println(search(arr, -1));
    }
}
