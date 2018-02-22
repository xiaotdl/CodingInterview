package kmp;

/**
 * Created by Xiaotian on 2/21/18.
 */
/**
 * Ref: https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * Do pattern matching using KMP algorithm
 * time: O(m + n) where m is length of text and n is length of pattern
 * space: O(n)
 */
public class Solution {
    /**
     * Slow method of pattern matching
     */
    public int strstr(char[] text, char[] pattern){
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }
            else {
                j = 0;
                k++;
                i = k;
            }
        }
        if (j == pattern.length) {
            return i - pattern.length;
        }
        return -1;
    }

    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length]; // lps indicates longest proper prefix which is also suffix.
        int i = 0;
        int j = 1;
        while (j < pattern.length) {
            if (pattern[i] == pattern[j]) {
                lps[j] = i + 1;
                i++;
                j++;
            }
            else {
                if (i != 0) {
                    i = lps[i - 1];
                }
                else {
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }

    public int KMP(char []text, char []pattern){
        int lps[] = computeTemporaryArray(pattern);
        int i = 0;
        int j = 0;
        while(i < text.length && j < pattern.length){
            if (text[i] == pattern[j]) {
                i++;
                j++;
            }
            else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == pattern.length) {
            return i - pattern.length ;
        }
        return -1;
    }

    public static void main(String args[]){
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";

        char[] S = str.toCharArray();
        char[] subS = subString.toCharArray();

        System.out.println(new Solution().strstr(S, subS));
        System.out.println(new Solution().KMP(S, subS));

    }
}
