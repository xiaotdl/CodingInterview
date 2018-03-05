package generate_primes;

import java.util.*;

/**
 * Created by Xiaotian on 3/2/18.
 */
class Solution {
    // n = num1 * num2, num1 <= sqrt(n), num2 >= sqrt(n)
    // tag: dfs
    // time: O(n*sqrt(n))
    // space: O(1)
    public List<Integer> getPrimesFrom1To(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getPrimesFrom1To(16));
        // [2, 3, 5, 7, 11, 13]
    }
}
