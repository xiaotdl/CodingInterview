public class SingleNumberII {
    /**
     * @param A : An integer array
     * @return : An integer
     */

    // V1, O(n), O(1)
    // Ternary
    public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int result = 0;
        int[] digits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                digits[i] += A[j] >> i & 1;
                digits[i] %= 3;
            }

            result |= digits[i] << i;
        }

        return result;
    }
}
