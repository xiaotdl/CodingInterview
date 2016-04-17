import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PermuteUnique {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */

    // V1, O(n!)
    // Recursion
    public static ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return result;
        }
        Collections.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.size()];
        helper(result, list, visited, nums);
        return result;
    }

    private static void helper(ArrayList<ArrayList<Integer>> result,
                               ArrayList<Integer> list,
                               boolean[] visited,
                               ArrayList<Integer> nums) {
        if (list.size() == nums.size()) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.size(); i++ ) {
            if (visited[i] || (i != 0 && nums.get(i) == nums.get(i-1) && visited[i-1] == false)){
                continue;
            }
            visited[i] = true;
            list.add(nums.get(i));
            helper(result, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 2, 2));
        System.out.println(permuteUnique(arr));
    }
}
