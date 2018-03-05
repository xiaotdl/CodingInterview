package test;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class PrettyPrint {
    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.printf("%2s ", m[i][j]);
            }
            System.out.println();
        }
    }
}
