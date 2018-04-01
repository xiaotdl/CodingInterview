package find_k_pairs_with_smallest_sums;

import java.util.*;

/**
 * Created by Xiaotian on 3/29/18.
 */
class Solution {
    // credit diagram: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    // tag: heap
    // time: O(klogk)
    // space: O(k)
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;

        PriorityQueue<int[]> q = new PriorityQueue<>(k, new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[2] - pair2[2];
            }
        });

        for (int i = 0; i < nums1.length && i < k; i++) {
            q.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        while (k-- > 0 && !q.isEmpty()) {
            int[] pair = q.poll();
            int i = pair[0];
            int j = pair[1];
            res.add(new int[]{nums1[i], nums2[j]});
            if (j == nums2.length - 1) continue;
            q.offer(new int[]{i, j + 1, nums1[i] + nums2[j + 1]});
        }
        return res;
    }
}
