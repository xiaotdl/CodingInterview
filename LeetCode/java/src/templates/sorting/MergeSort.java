package templates.sorting;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class MergeSort{
    // 归并排序
    // time:
    //   avg: O(nlogn)
    //   worst: O(nlogn)
    // space: O(n)
    // Steps:
    //   1. Recursively find middle point and divide the array into two halves until each array has one element
    //   2. Repeatedly merge sorted subarrays
    // MergeSort is a Divide and Conquer algorithm.
    // MergeSort is preferred over QuickSort for sorting LinkedLists, as it's more stable and we can't random access elements like in array.
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int l, int r) {
        if (l >= r) return;

        int m = l + (r - l) / 2;

        sort(nums, l, m);
        sort(nums, m + 1, r);

        merge(nums, l, m, r);
    }

    private void merge(int[] nums, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = nums[l + i];
        for (int j = 0; j < n2; j++) R[j] = nums[m + 1 + j];

        int i = 0, j = 0; // indexes of L and R
        int k = l; // index of merged array
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                nums[k] = L[i];
                i++;
            }
            else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,5,4,1,2};
        new MergeSortIterative().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

class MergeSortIterative {
    public void sort(int[] nums) {
        int n = nums.length;

        // merge subarrays bottom-up
        // curr_size: of subarrays to be merged
        for (int curr_size = 1; curr_size <= n - 1; curr_size = 2*curr_size) {
            for (int l = 0; l < n - 1; l += 2*curr_size) {
                int m = l + curr_size - 1;
                int r = Math.min(l + 2*curr_size - 1, n - 1);
                merge(nums, l, m, r);
            }
        }
    }

    private void merge(int[] nums, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = nums[l + i];
        for (int j = 0; j < n2; j++) R[j] = nums[m + 1 + j];

        int i = 0, j = 0; // indexes of L and R
        int k = l; // index of merged array
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                nums[k] = L[i];
                i++;
            }
            else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }
}
