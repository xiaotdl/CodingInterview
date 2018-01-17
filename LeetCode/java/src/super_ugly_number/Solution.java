package super_ugly_number;

/**
 * Created by Xiaotian on 12/27/16.
 */
// uglyFactor * uglyNum is still an uglyNum
// tag: dp/math
// time: O(nk)
// space: O(n+k)
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 0 || primes == null || primes.length == 0) return 0;
        int k = primes.length;
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int[] primePtrToUglyNum = new int[k];
        int[] primeIntoUglyNum = new int[k];
        for (int i = 0; i < k; i++) {
            primeIntoUglyNum[i] = primes[i];
        }
        for (int i = 1; i < n; i++) {
            int min = _min(primeIntoUglyNum);
            uglyNums[i] = min;
            for (int j = 0; j < k; j++) {
                if (primeIntoUglyNum[j] == min) {
                    primeIntoUglyNum[j] = uglyNums[++primePtrToUglyNum[j]] * primes[j];
                }
            }
        }
        return uglyNums[n - 1];
    }

    int _min(int[] list) {
        int min = Integer.MAX_VALUE;
        for (int x : list) {
            min = Math.min(min, x);
        }
        return min;
    }
}

class SolutionII {
    // Same as Solution
    /**
     * @param n a positive integer
     * @param primes the given prime list
     * @return the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ptrToUglys = new int[primes.length];
        int[] uglys = new int[n];
        uglys[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * uglys[ptrToUglys[j]]);
            }
            uglys[i] = min;

            for (int j = 0; j < ptrToUglys.length; j++) {
                if (uglys[ptrToUglys[j]] * primes[j] == min) {
                    ptrToUglys[j]++;
                }
            }
        }
        return uglys[n - 1];
    }
}