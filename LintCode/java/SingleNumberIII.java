import java.util.ArrayList;
import java.util.List;

public class SingleNumberIII {
    /**
     * @param A : An integer array
     * @return : Two integers
     */

    // V1, O(n), O(1)
    // XOR
    // Divide nums into 2 groups => 2*n1 + a, 2*n2 + b
    // a ^ b != 0, condition => find rightmost one bit that one set, the other not
    public List<Integer> singleNumberIII(int[] A) {
        List<Integer> result = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return result;
        }

        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }

        int bitDiffPos = xor & ~(xor - 1);

        int resA = 0;
        int resB = 0;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] & bitDiffPos) == 0) {
                resA ^= A[i];
            } else {
                resB ^= A[i];
            }
        }

        result.add(resA);
        result.add(resB);
        return result;
    }
}

