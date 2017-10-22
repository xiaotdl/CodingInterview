package number_of_complement;

/**
 * Created by Xiaotian on 10/21/17.
 */
public class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    public int findComplement(int num) {
        int mask = 0xffffffff;
        while ((mask & num) > 0) mask <<= 1;
        mask = ~mask;
        return num ^ mask;
    }
}
