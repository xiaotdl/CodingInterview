package maximum_swap;

/**
 * Created by Xiaotian on 9/14/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i; // records rightmost digit index
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    swap(digits, buckets[k], i);
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }

    private void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
