package lazy_caterer;

/**
 * Created by Xiaotian on 4/27/18.
 */
class Solution {
    // credit: https://www.geeksforgeeks.org/the-lazy-caterers-problem/
    // f(n): denote the maximum number of pieces by making n cuts.
    // f(0) = 1
    // f(1) = 2
    // ...
    // f(n) = n + f(n-1)

    // Expanding f(n-1) and so on we have,
    // f(n) = n + n-1 + n-2 + ...... + 1 + f(0)
    // which gives:
    // f(n) = (n*(n+1))/2 + 1
    public int findPieces(int n) {
        // Use the formula
        return (n * (n + 1)) / 2 + 1;
    }
}
