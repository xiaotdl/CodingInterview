package _1_bit_and_2_bit_characters;

/**
 * Created by Xiaotian on 3/19/18.
 */
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 0) i++;
            else i += 2;
        }
        return i == bits.length - 1;
    }
}
