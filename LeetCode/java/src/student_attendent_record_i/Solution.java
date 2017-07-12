package student_attendent_record_i;

/**
 * Created by Xiaotian on 7/11/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean checkRecord(String s) {
        int absentCnt = 0;
        int contdLateCnt = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'P') {
                contdLateCnt = 0;
            }
            else if (s.charAt(i) == 'A') {
                absentCnt++;
                if (absentCnt > 1) return false;
                contdLateCnt = 0;
            }
            else if (s.charAt(i) == 'L') {
                contdLateCnt++;
                if (contdLateCnt > 2) return false;
            }
            i++;
        }
        return true;
    }
}
