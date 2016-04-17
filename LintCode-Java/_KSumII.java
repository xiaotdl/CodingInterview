import java.util.ArrayList;
import java.util.Arrays;

public class _KSumII {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return a list of lists of integer
     */

    // V1, O(n!)?
    // Subsets, Recursion
    public ArrayList<ArrayList<Integer>> kSumII(int A[], int k, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (A == null || A.length == 0) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        Arrays.sort(A);
        helper(A, k, target, 0, path, result);
        return result;
    }
    private void helper(int[] A, int k, int target, int pos, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if (path.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }

        for (int i = pos; i < A.length; i++) {
            if (target - A[i] < 0) {
                break;
            }
            path.add(A[i]);
            helper(A, k, target - A[i], i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}


