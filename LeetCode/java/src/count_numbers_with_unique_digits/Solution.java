package count_numbers_with_unique_digits;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dp
// time: O(1)
// space: O(1)
// f(1) = 10
// f(2) = 9 * f(1)
// f(3) = 8 * f(2)
// ...
// f(10) = 1 * f(9)
// f(11) = 0
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int res = 10;
        int currNumsWithUniqDigits = 9;
        int availableNumbers = 9;
        while (n-- > 1 && availableNumbers > 0) {
            currNumsWithUniqDigits *= availableNumbers;
            res += currNumsWithUniqDigits;
            availableNumbers--;
        }
        return res;
    }
}
