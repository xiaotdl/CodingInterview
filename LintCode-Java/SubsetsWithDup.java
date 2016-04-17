import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubsetsWithDup {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */

    // V1, O(n2^n)?
    // Recursion
    public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.size() == 0) {
            return result;
        }
        Collections.sort(S);

        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(result, list, S, 0);
        return result;
    }

    private static void helper(ArrayList<ArrayList<Integer>> result,
                               ArrayList<Integer> list,
                               ArrayList<Integer> S,
                               int pos) {
        result.add(new ArrayList<Integer>(list));

        for (int i = pos; i < S.size(); i++) {
            if (i != pos && S.get(i) == S.get(i - 1)) {
                continue;
            }
            list.add(S.get(i));
            helper(result, list, S, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 2, 2));
        System.out.println(subsetsWithDup(arr));
    }

}
