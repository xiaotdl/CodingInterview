package templates.sorting;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class InsertionSort {
    // 插入排序
    // time:
    //   best:  O(n), sorted
    //   avg:   O(n^2)
    //   worst: O(n^2), sorted in reverse order
    // space: O(1)
    // Steps:
    //   Loop from i=1 to i=n-1:
    //     pick A[i] and insert/swap it into sorted sequence A[0..i-1]
    // InsertionSort is similar to people sorting cards.
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int j = i - 1;
            while (j >= 0 && curr < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = curr;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,5,4,1,2};
        new InsertionSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
