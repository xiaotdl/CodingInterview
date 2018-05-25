package read_n_characters_given_read4;

/**
 * Created by Xiaotian on 6/17/17.
 */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

class Reader4 {
    public int read4(char[] buf) {
        return 0;
    }
}

class Solution extends Reader4 {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    private int buffReadCnt = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        int readCnt = 0;
        boolean eof = false;
        while (!eof && readCnt < n) {
            if (buffReadCnt >= buffCnt) {
                buffReadCnt = 0;
                buffCnt = read4(buff);
                eof = buffCnt < 4;
            }
            int toReadCnt = Math.min(buffCnt - buffReadCnt, n - readCnt);
            for (int i = 0; i < toReadCnt; i++) {
                buf[readCnt++] = buff[buffReadCnt++];
            }
        }
        return readCnt;
    }
}

class SolutionII extends Reader4 {
    // credit: https://leetcode.com/problems/read-n-characters-given-read4/discuss/49496/Another-accepted-Java-solution

    char[] buf4 = new char[4];
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if (n == 0) return 0;

        int cnt = 0;
        while (true) {
            int buf4Cnt = read4(buf4);
            for (int i = 0; i < buf4Cnt; i++) {
                if (cnt == n) return n; // already read n chars
                buf[cnt++] = buf4[i];
            }
            if (buf4Cnt < 4) return cnt; // no more chars to read, EOF
        }
    }
}
