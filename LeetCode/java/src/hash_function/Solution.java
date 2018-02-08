package hash_function;

/**
 * Created by Xiaotian on 2/8/18.
 */
public class Solution {
    // hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33 + ascii(d)) % HASH_SIZE
    /**
     * @param key: A String you should hash
     * @param HASH_SIZE: An integer
     * @return an integer
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        if (key == null || key.length == 0) {
            return 0;
        }

        long sum = 0;
        for (char c : key) {
            int anscii = (int) c;
            sum = 33 * sum + anscii;
            sum %= HASH_SIZE;
        }

        return (int) sum;
    }
};

