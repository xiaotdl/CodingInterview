package shuffle_an_array;

import java.util.*;



/**
 * Created by Xiaotian on 2/12/18.
 */

// Similar to reservoir_sampling

// FY Shuffle和Reservoir Sampling的比较：
// +--------------+----------------+-----------------------------+------+-------+
// | ALGORITHM    | INPUT(S)       | OUTPUT                      | TIME | SPACE |
// +--------------+----------------+-----------------------------+------+-------+
// | FISHER-YATES | n elements     | A list containing all n     | O(n) | O(n)  |
// | SHUFFLE      |                | elements in a random order  |      |       |
// +--------------+----------------+-----------------------------+------+-------+
// | RESERVOIR    | n elements and | A set containing k of the n | O(n) | O(k)  |
// | SAMPLING     | an integer k   | elements, selected randomly |      |       |
// +--------------+----------------+-----------------------------+------+-------+

public class Solution {
    // Fisher–Yates shuffle Algorithm works in O(n) time complexity.
    // Ref: https://www.geeksforgeeks.org/shuffle-a-given-array/
    // 1. The probability that ith element goes to last position is 1/n
    // 2. The probability that ith element goes to second last position is: ((n-1)/n)*(1/(n-1))=1/n
    //   2.1. the first num didn't go to second last position: n - 1 / n
    //   2.2. the second num go to second last position: 1 / n - 1
    // 3. generalize from here...


    int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    // tag: math
    // time: O(n)
    // space: O(1)
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = nums.length;
        int[] shuffled = nums.clone();
        Random r = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            swap(shuffled, i, j);
        }
        return shuffled;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
