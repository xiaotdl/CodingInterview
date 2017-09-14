package can_place_flowers;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // greedily plant flower at every vacant spot encountered from left to right
    // tag: array
    // time: O(n)
    // space: O(1)
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) return false;
        if (n == 0) return true;

        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) continue;

            int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
            int prev = (i == 0) ? 0 : flowerbed[i - 1];
            if (prev == 0 && next == 0) {
                flowerbed[i] = 1;
                count++;
                if (count == n) return true;
            }
        }
        return false;
    }
}
