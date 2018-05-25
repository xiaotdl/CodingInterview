package sentence_screen_fitting;

/**
 * Created by Xiaotian on 4/5/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/sentence-screen-fitting/discuss/90874/12ms-Java-solution-using-DP/120488
    // My understanding:
    // 1) Based on the above observation, in the first for loop we compute the number of words that can be placed in the row if ith word is used as the starting word. This is saved as dp[i]. Note that this value can be greater than n.
    // 2) In the next for loop we calculate how many words are placed in each row based on dp[i]. Imagine placing the 0th word in the row-0, then this row will hold dp[0] words. Next, which word will be placed on the start of next row? We calculate that using dp[k] % n (Remember dp[i] can be greater than n).
    // time: O(n*(cols/lenAverage)) + O(rows)
    // where n is the length of sentence array, lenAverage is the average length of the words in the input array.

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        // dp[i]: starts row with ith word and dp[i] denotes numbers of words can be saved in current row, which can be greater than n
        int[] dp = new int[n];

        int maxRowLen = cols;
        for(int i = 0; i < n; i++) { // i: start word idx
            int currRowLen = 0, wordCnt = 0, wordIdx = i;
            while (currRowLen + sentence[wordIdx % n].length() <= maxRowLen) {
                currRowLen += sentence[wordIdx % n].length();
                currRowLen += 1; // space
                wordIdx++;
                wordCnt++;
            }
            dp[i] = wordCnt;
        }

        int wordCnt = 0;
        int wordIdx = 0;
        for(int i = 0; i < rows; i++) { //
            wordCnt += dp[wordIdx];
            wordIdx = (wordIdx + dp[wordIdx]) % n;
        }

        return wordCnt / n;
    }
}
