package utf_8_validation;

/**
 * Created by Xiaotian on 2/22/18.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;

        int i = 0;
        while (i < data.length) {
            int byte1 = data[i];
            // 0000 0000-0000 007F | 0xxxxxxx
            if (byte1 >> 7 == 0) {
                i += 1;
            }
            // 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
            else if (byte1 >> 5 == 0b110 && validBytes(data, i + 1, 1)) {
                i += 2;
            }
            // 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
            else if (byte1 >> 4 == 0b1110 && validBytes(data, i + 1, 2)) {
                i += 3;
            }
            // 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
            else if (byte1 >> 3 == 0b11110 && validBytes(data, i + 1, 3)) {
                i += 4;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private boolean validBytes(int[] data, int start, int cnt) {
        for (int i = 0; i < cnt; i++) {
            if (start + i >= data.length || data[start + i] >> 6 != 0b10) return false;
        }
        return true;
    }
}
