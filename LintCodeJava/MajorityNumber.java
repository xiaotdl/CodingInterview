import java.util.ArrayList;

public class MajorityNumber {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */

    // V1, O(n), O(1)
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int candidate = 0;
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (count == 0) {
                candidate = nums.get(i);
                count++;
                continue;
            }

            if (candidate == nums.get(i)) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
