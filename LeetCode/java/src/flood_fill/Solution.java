package flood_fill;

/**
 * Created by Xiaotian on 3/31/18.
 */
public class Solution {
    // tag: dfs
    // time: O(mn)
    // space: O(1)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;

        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    private void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        if (!(0 <= i && i < image.length && 0 <= j && j < image[0].length)) return;
        if (image[i][j] != oldColor) return;

        image[i][j] = newColor;

        for (int k = 0; k < 4; k++) {
            dfs(image, i + dx[k], j + dy[k], oldColor, newColor);
        }
    }
}
