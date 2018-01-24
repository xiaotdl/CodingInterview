package mirror_numbers;

import java.util.*;

/**
 * Created by Xiaotian on 1/23/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(1)
    /*
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // 0 1 2 3 4 5 6 7 8 9
        // 0 1 X X X X 9 X 8 6
        Map<Integer, Integer> mirrorNum = new HashMap<>();
        mirrorNum.put(0, 0);
        mirrorNum.put(1, 1);
        mirrorNum.put(6, 9);
        mirrorNum.put(8, 8);
        mirrorNum.put(9, 6);

        int l = 0;
        int r = num.length() - 1;
        while (l < r) {
            if (!mirrorNum.containsKey(num.charAt(l))) {
                return false;
            }
            if (mirrorNum.get(num.charAt(l)) != num.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
