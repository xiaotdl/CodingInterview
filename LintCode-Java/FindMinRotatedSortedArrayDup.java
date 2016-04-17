public class FindMinRotatedSortedArrayDup {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */

    // V1, O(n)
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }

        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            min = Math.min(min, num[i]);
        }

        return min;
    }
}
