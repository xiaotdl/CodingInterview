package input_stream;

/**
 * Created by Xiaotian on 3/7/18.
 */
public class Solution {
    // tag: string
    // time: O(n)
    // space: O(1)
    /**
     * @param inputA: Input stream A
     * @param inputB: Input stream B
     * @return: The answer
     */
    public String inputStream(String inputA, String inputB) {
        int i = inputA.length() - 1;
        int aDeleteCnt = 0;
        int j = inputB.length() - 1;
        int bDeleteCnt = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (inputA.charAt(i) == '<') {
                    aDeleteCnt++;
                    i--;
                }
                else { // char
                    if (aDeleteCnt > 0) {
                        aDeleteCnt--;
                        i--;
                    }
                    else break;
                }
            }
            while (j >= 0) {
                if (inputB.charAt(j) == '<') {
                    bDeleteCnt++;
                    j--;
                }
                else { // char
                    if (bDeleteCnt > 0) {
                        bDeleteCnt--;
                        j--;
                    }
                    else break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (inputA.charAt(i) != inputB.charAt(j)) return "NO";
                i--;
                j--;
            }
        }
        return (i == -1 && j == -1) ? "YES" : "NO";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().inputStream("a<b<c<d<e<f<g<h<i<j<k<l<m<n<", ""));
    }
}
