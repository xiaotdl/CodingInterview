import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Permute {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */

    // V1, O(n!)?
    // Recursion
    public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return result;
        }
        Collections.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(result, list, nums);
        return result;
    }

    private static void helper(ArrayList<ArrayList<Integer>> result,
                        ArrayList<Integer> list,
                        ArrayList<Integer> nums) {
        if (list.size() == nums.size()) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (list.contains(nums.get(i))) {
                continue;
            }

            list.add(nums.get(i));
            helper(result, list, nums);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        System.out.println(permute(arr));
    }
}
