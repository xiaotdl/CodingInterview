package rectangle_area;

/**
 * Created by Xiaotian on 3/19/18.
 */
public class Solution {
    // tag: geometry
    // time: O(1)
    // space: O(1)
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);

        int l = Math.max(A, E);
        int r = Math.min(C, G);
        int u = Math.min(D, H);
        int d = Math.max(F, B);

        // non-overlap
        if (C < E || A > G || B > H || D < F) return area1 + area2;
        // overlap
        return area1 + area2 - (r - l) * (u - d);
    }
}
