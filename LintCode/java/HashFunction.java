public class HashFunction {
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

