package excel_sheet_column_number;

/**
 * Created by Xiaotian on 3/14/18.
 */
public class Solution {
    // tag: math
    // time: O(n)
    // space: O(1)
    public int titleToNumber(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            num = num * 26 + c - 'A' + 1;
        }
        return num;
    }
}
