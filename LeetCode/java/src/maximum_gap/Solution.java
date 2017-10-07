package maximum_gap;

import java.util.*;

/**
 * Created by Xiaotian on 10/7/17.
 */
public class Solution {
    // credit: https://zhengyang2015.gitbooks.io/lintcode/maximum_gap_400.html
    // tag: bucket sort
    // time: O(n)
    // space: O(n)
    /**
     * @param nums: an array of integers
     * @return: the maximum difference
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;

        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        if (min == max) return 0;

        int bucketSize = (max - min) / n + 1;
        int bucketNum = (max - min) / bucketSize + 1;
        int[] bucketMin = new int[bucketNum];
        int[] bucketMax = new int[bucketNum];
        HashSet<Integer> bucketRegistry = new HashSet<>();
        for(int num : nums){
            int i = (num - min) / bucketSize;
            if(!bucketRegistry.contains(i)){
                bucketMin[i] = num;
                bucketMax[i] = num;
                bucketRegistry.add(i);
                continue;
            }
            bucketMin[i] = Math.min(bucketMin[i], num);
            bucketMax[i] = Math.max(bucketMax[i], num);
        }

        // 当num = min时就落在桶0里，所以第一个非空的桶一定为0
        int maxGap = 0;
        int prevBucketIndex = 0;
        for (int i = 1; i < bucketNum; i++) {
            if (!bucketRegistry.contains(i)) continue; // skip empty buckets
            int gap = bucketMin[i] - bucketMax[prevBucketIndex];
            maxGap = Math.max(maxGap, gap);
            prevBucketIndex = i;
        }
        return maxGap;
    }
}
