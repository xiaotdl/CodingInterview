package rotated_digits;

import java.util.*;

/**
 * Created by Xiaotian on 2/24/18.
 */
public class Solution {
    // tag: hash
    // time: O(n^2)
    // space: O(1)
    public int rotatedDigits(int N) {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (isGood(i) && rotated(i) != i) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isGood(int n) {
        Set<Integer> goodDigits = new HashSet<>(new ArrayList<>(Arrays.asList(0, 1, 8, 2, 5, 6, 9)));
        while (n != 0) {
            int digit = n % 10;
            if (!goodDigits.contains(digit)) return false;
            n /= 10;
        }
        return true;
    }

    private int rotated(int n) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        m.put(1, 1);
        m.put(8, 8);
        m.put(2, 5);
        m.put(5, 2);
        m.put(6, 9);
        m.put(9, 6);
        int rotated = 0;
        int base = 0;
        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            rotated += m.get(digit) * Math.pow(10, base);
            base++;
        }
        return rotated;
    }
}


