package templates.sorting;

import java.util.*;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class QuickSelect {
    // 快速选择
    // time:
    //   avg:   O(n)
    //   worst: O(n^2), e.g. all same nums
    // space: O(1), stack space: O(logn)
    // Steps:
    //   1. Pick a pivot and partition nums smaller to pivot's left and greater to pivot's right,
    //      partitioning is done through swapping smaller nums to left and return pivot index.
    //   2. Recursively select from subarray that contains kth largest num
    // QuickSelect is used to find kth smallest/largest num from unordered array in-place.
    public int kthLargestElement(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return -1;
        }

        return select(nums, 0, nums.length - 1, nums.length - k);

    }

    // k: kth largest num's final index
    public int select(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }

        int p = partition(nums, l, r);
        if (p == k) {
            return nums[p];
        } else if (p < k) {
            return select(nums, p + 1, r, k);
        }  else {
            return select(nums, l, p - 1, k);
        }
    }

    public int partition(int[] nums, int l, int r) {
        // Random rand = new Random();
        // int p = rand.nextInt(r - l + 1) + l;
        // int pivot = nums[p];
        // swap(nums, p, r);
        // ...
        // swap(nums, r, i + 1);

        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 10, 8, 9, 2, 3, 5, 6, 4};
        int k = 2;
        System.out.println(new QuickSelect().kthLargestElement(nums, k));
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1, 7, 10, 8, 9, 2, 3, 5, 6, 4};
        int k2 = 2;
        System.out.println(new QuickSelectIterative().kthLargestElement(nums2, k2));
        System.out.println(Arrays.toString(nums2));
    }
}

class QuickSelectIterative {
    public int kthLargestElement(int[] nums, int k) {
        return select(nums, k);
    }

    public int select(int[] nums, int k) {
        k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p < k) {
                l = p + 1;
            }
            else if (p > k) {
                r = p - 1;
            }
            else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
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
