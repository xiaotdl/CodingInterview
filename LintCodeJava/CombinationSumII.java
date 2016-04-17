import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    /**
     * @param candidates: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null) {
            return result;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper(candidates, target, list, 0, result);

        return result;
    }
    private static void helper(int[] candidates, int target, ArrayList<Integer> list, int pos, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        int prev = -1;
        for (int i = pos; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            // Skip duplicate numbers on same recursion tree level
            if (prev != -1 && prev == candidates[i]) {
                continue;
            }

            list.add(candidates[i]);
            helper(candidates, target - candidates[i], list, i + 1, result);
            list.remove(list.size() - 1);

            prev = candidates[i];
        }
    }
}

