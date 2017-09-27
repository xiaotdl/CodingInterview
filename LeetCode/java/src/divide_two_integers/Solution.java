package divide_two_integers;

/**
 * Created by Xiaotian on 9/26/17.
 */
public class Solution {
    // 1. 基本思想是不断地减掉除数，直到为0为止。但是这样会太慢。
    // 2. 我们可以使用2分法来加速这个过程。不断对除数*2，直到它比被除数还大为止。加倍的同时，也记录下cnt，将被除数减掉加倍后的值，并且结果+cnt。
    //    因为是2倍地加大，所以速度会很快，指数级的速度。
    // 3. 判断正负；越界返回最大值
    // tag: math, binary search
    // time: O(logn), n=dividend
    // space: O(1)
    public int divide(int dividend, int divisor) {
        long a = Math.abs((long)dividend); // 先取long否则Integer.MIN_VALUE越界
        long b = Math.abs((long)divisor);

        long res = 0;

        while (a >= b) {
            for (long multiB = b, cnt = 1; a >= multiB; multiB <<= 1, cnt <<= 1) {
                res += cnt;
                a -= multiB;
            }
        }

        int sign = (((dividend ^ divisor) >> 31) & 1) == 1 ? -1 : 1;
        res *= sign;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int)res;
    }
}

