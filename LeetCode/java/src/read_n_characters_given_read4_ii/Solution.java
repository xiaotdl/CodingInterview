package read_n_characters_given_read4_ii;

/**
 * Created by Xiaotian on 6/17/17.
 */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    // tag: str
    // time: O(n)
    // space: O(1)
    private int buffPtr = 0; // read buffered char count
    private int buffCnt = 0; // buffered char count
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr >= buffCnt) {
                buffPtr = 0;
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
        }
        return ptr;
    }
}

// placeholder
class Reader4 {
    public int read4(char[] buf) {
        return 0;
    }
}
