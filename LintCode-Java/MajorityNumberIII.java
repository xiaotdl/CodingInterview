import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MajorityNumberIII {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */

    // V1, O(n), O(k)
    // Hash
    public static int majorityNumber(ArrayList<Integer> nums, int k) {
        if (nums == null || nums.size() == 0 || k > nums.size()) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else if (map.size() < k) {
                map.put(num, 1);
            } else {
                ArrayList<Integer> removeList = new ArrayList<Integer>();
                for (Integer key : map.keySet()) {
                    removeList.add(key);
                }
                for (Integer key : removeList) {
                    map.put(key, map.get(key) - 1);
                    if (map.get(key) == 0) {
                        map.remove(key);
                    }
                }
            }
        }

        int result = 0;
        int maxCount = 0;
        for (Integer key : map.keySet()) {
            if (maxCount < map.get(key)) {
                maxCount = map.get(key);
                result = key;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(3, 1, 2, 4, 4));
        System.out.println(majorityNumber(arr, 3));
    }
}
