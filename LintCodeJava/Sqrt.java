public class Sqrt {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */

    // V1, O(logn)
    // Binary Search
    public int sqrt(int x) {
        if (x <= 1) {
            return x;
        }

        long low = 0;
        long high = x;

        while (low + 1 < high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid > x) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return (int) low;
    }
}

