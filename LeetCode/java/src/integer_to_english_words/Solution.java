package integer_to_english_words;

/**
 * Created by Xiaotian on 7/7/17.
 */
public class Solution {
    // tag: str, math
    // time: O(1)
    // space: O(1)
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String words = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }
        return words.trim();
    }

    // return numberToWords(num) where num < 1000;
    private String helper(int num) {
        if (num == 0) {
            return "";
        }
        else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        }
        else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        }
        else { // num < 1000
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}

class SolutionII {
    // Same as Solution
    // O(1)&O(1)
    // 数字英语单词分三组，UNDER20，TENS，THOUSANDS，然后分别按1000， 100， 10来数位分离，区间划分0..20, 20..100, 100..1000

    private final String[] UNDER20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String res = "";
        int thousandsIdx = 0;
        while (num != 0) {
            if (num % 1000 != 0) {
                res = numberToWordsUnder1000(num % 1000).trim() + " " + THOUSANDS[thousandsIdx] + " " + res;
            }
            num /= 1000;
            thousandsIdx++;
        }
        return res.trim();
    }

    private String numberToWordsUnder1000(int num) {
        if (num == 0) return "";
        else if (num < 20) return UNDER20[num];
        else if (num < 100) return (TENS[num / 10] + " " + UNDER20[num % 10]);
        else return UNDER20[num / 100] + " Hundred " + numberToWordsUnder1000(num % 100);
    }
}
