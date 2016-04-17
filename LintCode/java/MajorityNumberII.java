import java.util.ArrayList;

public class MajorityNumberII {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */

    // V1, O(n), O(1)
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int n1 = 0, n2 = 0;
        int cnt1 = 0, cnt2 = 0;
        for (int n : nums) {
            if (cnt1 != 0 && n == n1) {
                cnt1++;
            } else if (cnt2 != 0 && n == n2) {
                cnt2++;
            } else if (cnt1 == 0) {
                n1 = n;
                cnt1++;
            } else if (cnt2 == 0) {
                n2 = n;
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0; cnt2 = 0;
        for (int n : nums) {
            if (n == n1) {
                cnt1++;
            }
            if (n == n2) {
                cnt2++;
            }
        }

        return cnt1 > cnt2 ? n1 : n2;
    }
}


