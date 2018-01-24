package read_n_characters_given_read4_ii;

/**
 * Created by Xiaotian on 6/17/17.
 */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
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

// placeholder
class Reader4 {
    public int read4(char[] buf) {
        return 0;
    }
}

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

class SolutionII extends Reader4 {
    // tag: str, ptr, queue
    // time: O(n)
    // space: O(1)
    char[] sysBuf = new char[4];
    int sysBufHead = 0;
    int sysBufTail = 0;
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (sysBufHead == sysBufTail) { // q is empty
                sysBufHead = 0;
                sysBufTail = read4(sysBuf); // enqueue

                if (sysBufTail == 0) break; // no more to read
            }

            while (i < n && sysBufHead < sysBufTail) {
                buf[i++] = sysBuf[sysBufHead++]; // dequeue
            }
        }
        return i;
    }
}
