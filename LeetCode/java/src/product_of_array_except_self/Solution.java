package product_of_array_except_self;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // e.g. [1,2,3,4]
    // {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
    // { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }
    // tag: array
    // time: O(n)
    // space: O(n)
    public int[] productExceptSelf(int[] nums) {
        int p;
        int[] productLeft = new int[nums.length];
        p = 1;
        for (int i = 0; i < nums.length; i++) {
            productLeft[i] = p;
            p *= nums[i];
        }

        int[] productRight = new int[nums.length];
        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            productRight[i] = p;
            p *= nums[i];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = productLeft[i] * productRight[i];
        }
        return res;
    }
}
class SolutionII {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int p;

        // res = productLeft
        p = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }

        // res *= productRight
        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= p;
            p *= nums[i];
        }
        return res;
    }
}
