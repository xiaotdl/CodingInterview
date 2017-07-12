package complex_number_multiplication;

/**
 * Created by Xiaotian on 7/11/17.
 */
public class Solution {
    // tag: str, math
    // time: O(1)
    // space: O(1)
    // (x1+y1i) * (x2+y2i) = (x1x2 - y1y2) + (x1y2+x2y1)i.
    public String complexNumberMultiply(String a, String b) {
        int[] A = getNums(a);
        int[] B = getNums(b);
        return (A[0]*B[0] - A[1]*B[1]) + "+" + (A[1]*B[0] + A[0]*B[1]) +"i";

    }

    // x+iy => int[]{x, y}
    private int[] getNums(String s) {
        String[] S = s.split("\\+");
        int x = Integer.parseInt(S[0]);
        int y = Integer.parseInt(S[1].replace("i",""));
        return new int[]{x, y};
    }
}
