/**
 * Created by Xiaotian on 6/29/15.
 */
public class JumpGameII {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */

    // V1, O(n^2)
    // DP(sequence)
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int[] minSteps = new int[A.length];
        minSteps[0] = 0;

        for (int i = 1; i < A.length; i++) {
            minSteps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (A[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    minSteps[i] = Math.min(minSteps[i], minSteps[j] + 1);
                }
            }
        }

        return minSteps[A.length - 1];
    }
}


