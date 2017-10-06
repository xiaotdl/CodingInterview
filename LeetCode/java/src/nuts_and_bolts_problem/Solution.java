package nuts_and_bolts_problem;

import java.util.*;

/**
 * Created by Xiaotian on 10/6/17.
 */
class NBComparator {
    public int cmp(String a, String b) {
        if (a.equals(b)) return 0;
        return a.compareTo(b);
    }
}
/*
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
 */

public class Solution {
    // tag: sort
    // time: O(nlogn)
    // space: O(1)
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || nuts.length == 0 || bolts == null || bolts.length == 0) return;
        if (nuts.length != bolts.length) return;

        qsort(nuts, bolts, 0, nuts.length - 1, compare);
    }

    private void qsort(String[] nuts, String[] bolts, int l, int r, NBComparator compare) {
        if (l >= r) return;

        int p = partition(nuts, bolts[r], l, r, compare);
        partition(bolts, nuts[p], l, r, compare);

        qsort(nuts, bolts, l, p - 1, compare);
        qsort(nuts, bolts, p + 1, r, compare);
    }

    private int partition(String[] A, String pivot, int l, int r, NBComparator compare) {
        int i = l; // ptr to nums < pivot
        int j = l; // iterating ptr
        while (j < r) {
            if (compare.cmp(A[j], pivot) == -1 || compare.cmp(pivot, A[j]) == 1) { // <
                swap(A, j, i);
                i++;
                j++;
            } else if (compare.cmp(A[j], pivot) == 0 && compare.cmp(A[r], pivot) != 0) { // ==
                swap(A, j, r);
            } else {
                j++;
            }
        }
        swap(A, i, r);
        return i;
    }

    private void swap(String[] A, int i, int j) {
        String tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        String[] nuts = new String[]{"@", "#", "$", "%", "^", "&"};
        String[] bolts = new String[]{"$", "%", "&", "^", "@", "#"};
        new Solution().sortNutsAndBolts(nuts, bolts, new NBComparator());
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));

    }
};
