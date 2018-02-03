package bulls_and_cows;

/**
 * Created by Xiaotian on 2/2/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(1)
    public String getHint(String secret, String guess) {
        int bulls = 0; // A
        int cows = 0;  // B
        int[] nums = new int[10]; // secret==>++, guess==>--
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            }
            else {
                if (nums[s] < 0) cows++;
                if (nums[g] > 0) cows++;
                nums[s]++;
                nums[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
