package third_maxmum_number;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class Solution {
    // tag: array, hash
    // time: O(n)
    // space: O(1)
    public int thirdMax(int[] nums) {
        Integer m1 = null;
        Integer m2 = null;
        Integer m3 = null;
        for (Integer num : nums) {
            if (num.equals(m1) || num.equals(m2) || num.equals(m3)) continue;
            if (m1 == null || num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            }
            else if (m2 == null || num > m2) {
                m3 = m2;
                m2 = num;
            }
            else if (m3 == null || num > m3) {
                m3 = num;
            }
        }
        return m3 == null ? m1 : m3;
    }
}
