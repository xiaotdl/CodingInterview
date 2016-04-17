public class JumpGame {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */

    // V1, O(n^2)
    // DP(sequence)
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }

        boolean[] canJump = new boolean[A.length];
        canJump[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (canJump[j] && j + A[j] >= i) {
                    canJump[i] = true;
                    break;
                }
            }
        }

        return canJump[A.length - 1];
    }
}


