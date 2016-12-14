package first_bad_version;

/**
 * Created by Xiaotian on 12/14/16.
 */
// tag: binary search
// time: O(logn)
// space: O(1)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
public class Solution {
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (isBadVersion(start)) return start;
        if (isBadVersion(end)) return end;
        return -1;
    }

    public boolean isBadVersion(int n) {
        return n >= 100;
    }
}
