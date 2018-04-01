package happy_number;

import java.util.*;

/**
 * Created by Xiaotian on 2/4/18.
 */
public class Solution {
    // tag: math, 数位分离
    // time: O(n)
    // space: O(n)
    /*
     * @param n: An integer
     * @return: true if this is a happy number or false
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        seen.add(n);

        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = sum;
            if (seen.contains(n)) {
                return false;
            }
            seen.add(n);
        }

        return true;
    }
}

class SolutionII {
    // Same as Solution
    // tag: math, 数位分离
    // time: O(n)
    // space: O(n)
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        seen.add(n);
        while (n != 1) {
            int nextN = 0;
            while (n != 0) {
                int digit = n % 10;
                n /= 10;
                nextN += digit * digit;
            }
            if (seen.contains(nextN)) return false;
            seen.add(nextN);
            n = nextN;
        }
        return true;
    }
}
