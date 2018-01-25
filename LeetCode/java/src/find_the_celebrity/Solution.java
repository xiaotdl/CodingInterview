package find_the_celebrity;

/**
 * Created by Xiaotian on 6/26/17.
 */
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public int findCelebrity(int n) {
        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (knows(i, j)) {
                i++;
            }
            else {
                j--;
            }
        }
        for (j = 0; j < n; j++) {
            if (i != j && (knows(i, j) || !knows(j, i))) {
                return -1;
            }
        }
        return i;
    }
}

class Relation {
    boolean knows(int i, int j) {
        return true;
    }
};


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

class SolutionII extends Relation {
    // tag: array
    // time: O(n)
    // space: O(1)
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) { // candidate is not a celebrity
                candidate = i;
            }
        }

        // verify candidate is a celebrity
        for (int i = 0; i < n && i != candidate; i++) {
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }

        return candidate;
    }
}

