package excel_sheet_column_title;

/**
 * Created by Xiaotian on 3/14/18.
 */
class Solution {
    // tag: math
    // time: O(1)
    // space: O(1)
    public String convertToTitle(int n) {
        String res = "";
        while (n != 0) {
            res = (char)('A' + (n - 1) % 26) + res;
            n = (n - 1) / 26;
        }
        return res;
    }
}
