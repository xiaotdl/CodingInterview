import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */

    // V1, O(n*2^n)
    // Subsets, Recursion
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            helper(candidates, target - candidates[i], list, i, result);
            list.remove(list.size() - 1);

            prev = candidates[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(combinationSum(arr, 7));
    }
}

