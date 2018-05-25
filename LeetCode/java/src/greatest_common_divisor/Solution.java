package greatest_common_divisor;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // http://www.crazyforcode.com/euclids-algorithm-greatest-common-divisor-gcd/
    // tag: euclids algorithm, math
    // time: O(log min(a,b))
    // space: O(1)
    public int getGCD(int a, int b) { // greatest common divisor/factor
        if (b == 0) return a;
        else return getGCD(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getGCD(14, 21));
        System.out.println(new Solution().getGCD(660, 10000));
        System.out.println(660/20);
        System.out.println(10000/20);

    }
}

