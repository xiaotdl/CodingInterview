package templates.sorting;

import java.util.Arrays;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class QuickSort {
    // 快速排序 - top down
    // time:
    //   best:  O(nlogn)
    //   avg:   O(nlogn)
    //   worst: O(n^2), e.g. all same nums
    // space: O(1), stack space: O(logn)
    // Steps:
    //   1. Pick a pivot and partition nums smaller to pivot's left and greater to pivot's right,
    //      partitioning is done through swapping smaller nums to left and return pivot index.
    //   2. Recursively applies step 1 to left and right subarray
    // QuickSort is a Divide and Conquer algorithm.
    // QuickSort is preferred over MergeSort for sorting Arrays, as it's in-place.

    //             ArrayList  LinkedList InPlace(Space) Stable(Time)
    // QuickSort   Yes                   Yes
    // MergeSort              Yes                       Yes
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int r) {
        if (l >= r) return;

        int p = partition(nums, l, r);
        sort(nums, l, p - 1);
        sort(nums, p + 1, r);
    }

    // returns index of pivot
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r]; // use randomized position instead of last one for optimization
        int i = l; // starting index of smaller elements
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,8,9,10,7};
        new QuickSort().sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}

class QuickSortIterative {
    public void sort(int[] nums, int l, int r) {
        int[] stack = new int[r - l + 1];
        int top = -1;

        // init stack
        stack[++top] = l;
        stack[++top] = r;

        // keep popping elements until stack is empty
        while (top > 0) {
            // pop l and r
            r = stack[top--];
            l = stack[top--];

            int p = partition(nums, l, r); // pivot index
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }
            if (p + 1 < r) {
                stack[++top] = p + 1;
                stack[++top] = r;
            }
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
