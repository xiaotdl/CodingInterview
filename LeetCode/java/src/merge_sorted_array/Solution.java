package merge_sorted_array;

/**
 * Created by Xiaotian on 9/5/17.
 */
public class Solution {
    // tag: array, dp
    // time: O(n^2)
    // space: O(n^2)
    /**
     * @param nums1: sorted integer array which has m elements, with size of m+n
     * @param nums2: sorted integer array which has n elements, with size of n
     * @return: void
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }
            else {
                nums1[k--] = nums2[j--];
            }
        }
        while (i >= 0) nums1[k--] = nums1[i--];
        while (j >= 0) nums1[k--] = nums2[j--];
    }
}
