package weighted_random_sampling;

import java.util.*;

/**
 * Created by Xiaotian on 4/15/18.
 */
class Solution {
    // credit: http://blog.gainlo.co/index.php/2016/11/11/uber-interview-question-weighted-random-numbers/

    // tag: brutal force
    // time:
    //    construct: O(n)
    //    query: O(n)
    // space: O(1)
    public int weightedRandomSampling1(int[] A, int[] W) {
        int n = A.length;
        int sum = 0;
        for (int weight : W) sum += weight;
        Random rand = new Random();
        int selection = rand.nextInt(sum) + 1;

        int currSum = 0;
        int idxSelected = -1;
        for (int i = 0; i < n; i++) {
            if (currSum < selection && selection <= currSum + W[i]) {
                idxSelected = i;
                break;
            }
            currSum += W[i];
        }
        return A[idxSelected];
    }

    // tag: binary search
    // time:
    //    construct: O(n)
    //    query: O(logn)
    // space: O(n)
    public int weightedRandomSampling2(int[] A, int[] W) {
        int n = A.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i - 1 >= 0 ? sum[i - 1] : 0) + W[i];
        }
        Random rand = new Random();
        int selection = rand.nextInt(sum[n - 1]) + 1;

        // binarySearchFirstLargerOrEq(sum[], selection);
        return 0;
    }

    // tag: array
    // time:
    //    construct: O(n)
    //    query: O(1)
    // space: O(sum of all weights)
    public int weightedRandomSampling3(int[] A, int[] W) {
        int n = A.length;
        int sum = 0;
        for (int weight : W) sum += weight;

        int[] weightedA = new int[sum];
        int i = 0;
        for (int j = 0; j < n; j++) {
            int weight = W[j];
            while (weight-- > 0) weightedA[i++] = A[j];
        }

        Random rand = new Random();
        int selection = rand.nextInt(sum);

        return weightedA[selection];
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3};
        int[] W = new int[]{3, 2, 1};

        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 1; i <= 3; i++) m.put(i, 0);

        Solution solution = new Solution();
        for (int i = 0; i < 10000; i++) {
            int x = solution.weightedRandomSampling1(A, W);
            m.put(x, m.get(x) + 1);
        }
        System.out.println(m);
    }
}
