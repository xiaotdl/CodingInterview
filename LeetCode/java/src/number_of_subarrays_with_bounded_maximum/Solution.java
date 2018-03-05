package number_of_subarrays_with_bounded_maximum;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return countSubarrayMaxNumEqOrUnder(A, R) - countSubarrayMaxNumEqOrUnder(A, L - 1);
    }

    private int countSubarrayMaxNumEqOrUnder(int[] A, int upper) {
        int cnt = 0;
        int localCnt = 0;
        for(int x : A){
            if(x <= upper) {
                localCnt++;
            } else {
                localCnt = 0;
            }
            cnt += localCnt;
        }
        return cnt;
    }
}
