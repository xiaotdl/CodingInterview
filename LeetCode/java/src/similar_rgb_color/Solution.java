package similar_rgb_color;

/**
 * Created by Xiaotian on 3/17/18.
 */
public class Solution {
    // tag: brutal force
    // time: O(n)
    // space: O(1)
    public String similarRGB(String color) {
        int r1 = Character.digit(color.charAt(1), 16);
        int r2 = Character.digit(color.charAt(2), 16);
        int g1 = Character.digit(color.charAt(3), 16);
        int g2 = Character.digit(color.charAt(4), 16);
        int b1 = Character.digit(color.charAt(5), 16);
        int b2 = Character.digit(color.charAt(6), 16);

        int r = getMinDiff(r1, r2);
        int g = getMinDiff(g1, g2);
        int b = getMinDiff(b1, b2);
        return "#"+toHex(r)+toHex(r)+toHex(g)+toHex(g)+toHex(b)+toHex(b);
    }

    private String toHex(int x) {
        return Integer.toHexString(x);
    }

    private int getMinDiff(int r1, int r2) {
        int minDiff = Integer.MAX_VALUE;
        int minR = -1;
        for (int r = 0; r <= 15; r++) {
            int diff = Math.abs(r*16 + r - r1*16 - r2);
            if (diff < minDiff) {
                minDiff = diff;
                minR = r;
            }
        }
        return minR;
    }
}

