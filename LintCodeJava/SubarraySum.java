import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     * and the index of the last number
     */

    // V1, O(n^2)
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == 0) {
                    result.add(i);
                    result.add(j);
                    return result;
                }
            }
        }

        return result;
    }

    // V2, O(n), O(n)
    // Hash, PrefixSum
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Map<Integer, Integer> prefixSumMap = new HashMap<Integer, Integer>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum == 0) {
                result.add(0);
                result.add(i);
                break;
            }

            if (prefixSumMap.containsKey(sum)) {
                result.add(prefixSumMap.get(sum) + 1);
                result.add(i);
                break;
            } else {
                prefixSumMap.put(sum, i);
            }
        }

        return result;
    }
}

