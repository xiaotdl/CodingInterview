package smallest_rectangle_enclosing_black_pixels;

import java.util.*;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // tag: dfs
    // time: O(mn)
    // space: O(mn)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> shape = new ArrayList<>();
        dfs(image, visited, x, y, shape);
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] pixel : shape) {
            minX = Math.min(minX, pixel[0]);
            maxX = Math.max(maxX, pixel[0]);
            minY = Math.min(minY, pixel[1]);
            maxY = Math.max(maxY, pixel[1]);
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    private final static int[] dx = {0, 0, 1, -1};
    private final static int[] dy = {1, -1, 0, 0};
    private void dfs(char[][] image, boolean[][] visited, int x, int y, List<int[]> shape) {
        int m = image.length;
        int n = image[0].length;
        if (!(0 <= x && x < m && 0 <= y && y < n)) return;
        if (image[x][y] == '0') return;
        if (visited[x][y]) return;
        visited[x][y] = true;

        shape.add(new int[]{x, y});

        for (int k = 0; k < 4; k++) {
            dfs(image, visited, x + dx[k], y + dy[k], shape);
        }
    }
}
