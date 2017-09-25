package wood_cut;

/**
 * Created by Xiaotian on 9/25/17.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    /*
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k < 0) return 0;

        int maxL = L[0];
        for (int l : L) {
            maxL = Math.max(maxL, l);
        }

        int l = 1;
        int r = maxL;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (pieceCnt(L, m) >= k) {
                l = m;
            } else {
                r = m;
            }
        }

        if (pieceCnt(L, r) >= k) return r;
        if (pieceCnt(L, l) >= k) return l;
        return 0;
    }

    private int pieceCnt(int[] L, int length) {
        int cnt = 0;
        for (int l : L) {
            cnt += l / length;
        }
        return cnt;
    }
}

