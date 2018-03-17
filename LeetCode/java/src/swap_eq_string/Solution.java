package swap_eq_string;

import java.util.*;

/**
 * Created by Xiaotian on 3/11/18.
 */

// Check if two strings can equal after one swap of two chars
// e.g.
// conSerVe
// conVerSe
// ==> true
public class Solution {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public boolean swapEq(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int i = 0;
        while (i < A.length && A[i] == B[i]) i++;
        if (i == A.length) return false; // two strs r equal

        int j = A.length - 1;
        while (j >= 0 && A[j] == B[j]) j--;
        swap(A, i, j);

        for (int k = i; k <= j; k++) {
            if (A[k] != B[k]) return false;
        }
        return true;
    }
    private void swap(char[] A, int i, int j) {
        char tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().swapEq("abc",
                                                 "abc"));
        System.out.println(new Solution().swapEq("conSerVe",
                                                 "conVerSe"));
        System.out.println(new Solution().swapEq("conSeYrVe",
                                                 "conVeXrSe"));
    }
}

// follow up: multiple swap
// e.g.
// conSSSerBVVe
// conVVBerSSSe
// ==> true
class SolutionII {
    // tag: hash
    // time: O(n)
    // space: O(n)
    public boolean swapEq(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        Map<Character, Map<Character, Integer>> m = new HashMap<>(); // bChar2<aChar2swapNeededCnt>
        int swapNeededCnt = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != B[i]) {
                if (m.containsKey(A[i]) && m.get(A[i]).containsKey(B[i])) {
                    m.get(A[i]).put(B[i], m.get(A[i]).get(B[i]) - 1);
                    if (m.get(A[i]).get(B[i]) == 0) m.get(A[i]).remove(B[i]);
                    swapNeededCnt--;
                    continue;
                }
                swapNeededCnt++;
                m.putIfAbsent(B[i], new HashMap<>());
                m.get(B[i]).put(A[i], m.get(B[i]).getOrDefault(A[i], 0) + 1);
            }
        }
        return swapNeededCnt == 0;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionII().swapEq("conSSSerBVVe",
                                                   "conVVBerSSSe"));
        System.out.println(new SolutionII().swapEq("conSSSerBVVe",
                                                   "conXVBerSSSe"));
    }
}
