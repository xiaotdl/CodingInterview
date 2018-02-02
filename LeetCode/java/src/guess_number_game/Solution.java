package guess_number_game;

/**
 * Created by Xiaotian on 2/1/18.
 */
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    /**
     * @param n an integer
     * @return the number you guess
     */
    public int guessNumber(int n) {
        int s = 1;
        int e = n;
        while (s + 1 < e) {
            int m = s + (e - s) / 2;
            int ret = guess(m);
            if (ret == 0) {
                return m;
            }
            else if (ret == 1) {
                s = m;
            }
            else {
                e = m;
            }
        }

        if (guess(s) == 0) return s;
        if (guess(e) == 0) return e;

        return -1;
    }
}

class GuessGame {
    public int guess(int x) {
        return 0;
    }
}
