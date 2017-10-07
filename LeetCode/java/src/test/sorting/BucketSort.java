package test.sorting;

import java.util.*;

/**
 * Created by Xiaotian on 9/12/17.
 */
public class BucketSort {
    // 桶排序
    // time:
    //   best:  O(n+k)
    //   avg:   O(n+k)
    //   worst: O(n^2)
    // space: O(n)
    // Steps:
    //   1. get bucket numbers and bucket size
    //   2. add numbers into buckets
    //   3. sort numbers in each bucket
    // BucketSort is useful when input is uniformly distributed over a range.
    public void sort(int[] nums, int bucketNum) {
        int k = bucketNum;

        // calculate bucket size
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int bucketSize = (max - min) / bucketNum + 1; // +1 otherwise max number will OutOfIndexBound

        // init buckets
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<Integer>());
        }

        // add num into buckets
        for (int i = 0; i < nums.length; i++) {
            int bucketIndex = (nums[i] - min) / bucketSize;
            bucketList.get(bucketIndex).add(nums[i]);
        }

        // sort nums in each buckets and overwrite the orig array
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            insertionSort(bucketList.get(i));
            for (int num : bucketList.get(i)) {
                nums[index++] = num;
            }
        }
    }

    private void insertionSort(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) return;

        for (int i = 1; i < nums.size(); i++) {
            int curr = nums.get(i);
            int j = i - 1;
            while (j >= 0 && curr < nums.get(j)) {
                nums.set(j + 1, nums.get(j));
                j--;
            }
            nums.set(j + 1, curr);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{78, 17, 39, 26, 72, 94, 21, 12, 23, 68};
        new BucketSort().sort(nums, 10);
        System.out.println(Arrays.toString(nums));
    }
}
