import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {


    // V1, O(?)
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[] nums = {1,2,3,4,5,6,7,8,9};

        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(nums, k, n, list, 0, result);

        return result;
    }

    private void helper(int[] nums, int len, int target, ArrayList<Integer> list, int pos, List<List<Integer>> result) {
        if (list.size() == len && target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        if (list.size() > len) {
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }

            list.add(nums[i]);
            helper(nums, len, target - nums[i], list, i + 1, result);
            list.remove(list.size() - 1);
        }
    }
}

