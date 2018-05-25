package h_index_ii;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // binary search the first index from the sorted array so that:
    // citations[index] >= length(citations) - index
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        int l = 0;
        int r = citations.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (citations[m] >= citations.length - m) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (citations[l] >= citations.length - l) return citations.length - l;
        if (citations[r] >= citations.length - r) return citations.length - r;
        return 0;
    }
}
