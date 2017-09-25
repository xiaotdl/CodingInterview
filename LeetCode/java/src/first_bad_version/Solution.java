package first_bad_version;

/**
 * Created by Xiaotian on 12/14/16.
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public int firstBadVersion(int n) {
        int l = 0;
        int r = n;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        if (isBadVersion(l)) return l;
        if (isBadVersion(r)) return r;
        return -1;
    }

    public boolean isBadVersion(int n) {
        return n >= 100;
    }
}
