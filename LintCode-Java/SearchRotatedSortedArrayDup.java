public class SearchRotatedSortedArrayDup {
    /**
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean
     */

    // V1, O(n)
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return true;
            }
        }

        return false;
    }
}
