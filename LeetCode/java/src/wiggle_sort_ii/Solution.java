package wiggle_sort_ii;

/**
 * Created by Xiaotian on 10/6/17.
 */
public class Solution {
    // tag: partition
    // time: O(n)
    // space: O(n)
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int avg = findKthLargest(nums, n / 2 == 0 ? 1 : n / 2);

        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            ans[i] = avg;
        }

        int l, r;
        if (nums.length % 2 == 0) {
            l = nums.length - 2;
            r = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < avg) {
                    ans[l] = nums[i];
                    l -= 2;
                } else if (nums[i] > avg) {
                    ans[r] = nums[i];
                    r += 2;
                }
            }
        } else {
            l = 0;
            r = nums.length - 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < avg) {
                    ans[l] = nums[i];
                    l += 2;
                } else if (nums[i] > avg) {
                    ans[r] = nums[i];
                    r -= 2;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }

    private int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p > k) {
                r = p - 1;
            } else if (p < k) {
                l = p + 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i, j;
        for (i = l, j = l; j < r; j++) {
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
}
