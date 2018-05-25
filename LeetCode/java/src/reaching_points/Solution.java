package reaching_points;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/reaching-points/discuss/114732/Java-Simple-solution-with-explanation
    // We can solve this problem recursively from tx and ty.
    // In each recursive call, if tx > ty, it should derive from (tx % ty, ty), otherwise, from (tx, ty % tx) because the bigger one must come from last transformation from (tx - ty, ty) or (tx, ty - tx) until it is less than the smaller number.
    // We only need to care about the situation where (sx, sy) transforms to (sx + n * sy, sy) or (sx, sy + m * sx) in the first time because sx > sy is not the result of (sx % sy + m * sy). Hence, if sx > sy, (sx + n * sy) % sy will give a smaller number which is wrong.
    // tag: math
    // time: O(log min(tx,ty))
    // space: O(1)
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
//        System.out.println(String.format("%s %s %s %s", sx, sy, tx, ty));
        if (sx > tx || sy > ty) return false;
        if (sx == tx && (ty - sy) % sx == 0) return true;
        if (sy == ty && (tx - sx) % sy == 0) return true;
        return reachingPoints(sx, sy, tx % ty, ty % tx);
    }
}

