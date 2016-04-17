/**
 * public class VersionControl {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use VersionControl.isBadVersion(k) to judge whether
 * the kth code version is bad or not.
 */
public class FindFirstBadVersion {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */

    // V1, O(logn)
    // Iteration, Binary Search
    public static int findFirstBadVersion(int n) {
        int start = 1;
        int end = n;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (VersionControl.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (VersionControl.isBadVersion(start)) {
            return start;
        }
        if (VersionControl.isBadVersion(end)) {
            return end;
        }

        return 0;
    }

    public static class VersionControl {
        public static boolean isBadVersion(int k) {
            return k > 3;
        };
    }

    public static void main(String[] args) {
        System.out.println(findFirstBadVersion(5));
    }
}

