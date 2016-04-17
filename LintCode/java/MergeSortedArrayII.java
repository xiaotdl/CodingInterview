import java.util.ArrayList;
import java.util.Collections;

public class MergeSortedArrayII {
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */

    // V1, O((m+n)log(m+n))
    public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        for (int i = 0; i < B.size(); i++) {
            A.add(B.get(i));
        }
        Collections.sort(A);
        return A;
    }

    // V2, O(mlogn)
}
