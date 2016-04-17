public class FindMinRotatedSortedArray {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */

    // V1, O(logn)
    // Iteration, Binary Search
    public static int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }

        int start = 0;
        int end = num.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (num[mid] > num[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return Math.min(num[start], num[end]);
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 0, 1, 2};
        System.out.println(findMin(arr));
    }
}
