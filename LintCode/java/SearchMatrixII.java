public class SearchMatrixII {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */

    // V1, O(m+n)
    public static int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int count = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1;
        int j = 0;

        while (i >= 0 && j < col) {
            if (matrix[i][j] == target) {
                count++;
                i--;
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {2, 4, 7, 8}, {3, 5, 9, 10}};
        System.out.println(searchMatrix(matrix, 3));
    }
}
