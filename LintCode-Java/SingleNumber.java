public class SingleNumber {
    /**
     *@param A : an integer array
     *return : a integer
     */

    // V1, O(n), O(1)
    // XOR
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A[0];
        for(int i = 1; i < A.length; i++) {
            n = n ^ A[i];
        }

        return n;
    }
}

