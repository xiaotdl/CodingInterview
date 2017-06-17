package read_n_characters_given_read4;

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
    public int read(char[] buf, int n) {
        boolean eof = false;
        int total = 0; // total bytes read so far
        char[] tmp = new char[4]; // tmp buffer

        while (!eof && total < n) {
            int count = read4(tmp);
            eof = count < 4; // in case eof
            count = Math.min(count, n - total); // in case n chars in the middle of file
            for (int i = 0; i < count; i++) {
                buf[total++] = tmp[i];
            }
        }

        return total;
    }
}

// placeholder
class Reader4 {
    public int read4(char[] buf) {
        return 0;
    }
}
