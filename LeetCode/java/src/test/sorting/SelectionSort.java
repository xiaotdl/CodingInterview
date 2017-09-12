package test.sorting;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class SelectionSort {
    // 选择排序
    // time:
    //   best:  O(n^2)
    //   avg:   O(n^2)
    //   worst: O(n^2)
    // space: O(1)
    // Steps:
    //   keep finding minimum num and append it to the end of sorted sequence
    // SelectionSort never swap more than n times, it's used when memory write is costly
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, min, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,5,4,1,2};
        new SelectionSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
