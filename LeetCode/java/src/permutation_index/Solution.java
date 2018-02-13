package permutation_index;

/**
 * Created by Xiaotian on 2/12/18.
 */
public class Solution {
    // 康拓展开
    // Ref: https://zh.wikipedia.org/wiki/%E5%BA%B7%E6%89%98%E5%B1%95%E5%BC%80
    // 357412968 展开为 98884。因为X=2*8!+3*7!+4*6!+2*5!+0*4!+0*3!+2*2!+0*1!+0*0!=98884.
    // for i in 0..n-1:
    //     cnt += cntSmallerNumsRHS[i] * factorials[n - 1 - i];
    // tag: array, permutation
    // time: O(n)
    // space: O(n)
    /*
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        int n = A.length;
        // factorials[i]: i!
        long[] factorials = new long[n + 1];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = i * factorials[i - 1];
        }

        // cntSmallerNumsRHS[i]: cnt of smaller nums than A[i] on right side,  A[i+1..n-1]
        int[] cntSmallerNumsRHS = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[i]) cntSmallerNumsRHS[i]++;
            }
        }

        long cnt = 0; // smaller permutations
        for (int i = 0; i < n; i++) {
            cnt += cntSmallerNumsRHS[i] * factorials[n - 1 - i];
        }
        long index = cnt + 1;
        return index;
    }
}
