package text_justification;

import java.util.*;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // TODO
    // credit: https://discuss.leetcode.com/topic/9147/simple-java-solution
    // tag: str
    // time: O(n)
    // space: O(1)
    public List<String> fullJustify(String[] words, int L) {
        List<String> lines = new ArrayList<String>();

        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }


        return lines;
    }
}

class SolutionII {
    // tag: str
    // time: O(n)
    // space: O(1)
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;

        int endWordIdx = -1;
        int startWordIdx = 0;
        while (startWordIdx < words.length) {
            endWordIdx = processLine(words, startWordIdx, L);
            addLine(res, words, startWordIdx, endWordIdx, L);
            startWordIdx = endWordIdx + 1;
        }
        return res;
    }

    private int processLine(String[] words, int startWordIdx, int L) {
        int lineWordCnt = 0;
        int lineLen = words[startWordIdx].length();
        int prevWordIdx = startWordIdx;
        lineWordCnt++;
        while(prevWordIdx + 1 < words.length
                && lineLen + 1 + words[prevWordIdx + 1].length() <= L) {
            lineLen += 1 + words[prevWordIdx + 1].length();
            prevWordIdx++;
            lineWordCnt++;
        }
        return prevWordIdx;
    }

    private void addLine(List<String> res, String[] words, int s, int e, int L) {
        boolean isLastLine = (e == words.length - 1 ? true : false);

        int wordCnt = e - s + 1;
        int wordsLen = 0;
        for (int i = s; i <= e; i++) {
            wordsLen += words[i].length();
        }
        int spaceCnt = L - wordsLen;
        int intervalCnt = wordCnt - 1;

        if (intervalCnt == 0) {
            res.add(words[s] + nChars(spaceCnt, ' '));
            return;
        }

        if (isLastLine) {
            String line = words[s];
            for (int i = s + 1; i <= e; i++) {
                line += " " + words[i];
            }
            line += nChars(spaceCnt - (wordCnt - 1), ' ');
            res.add(line);
            return;
        }

        int[] spaces = new int[intervalCnt];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] += spaceCnt / intervalCnt;
            if (i < spaceCnt % intervalCnt) spaces[i]++;
        }

        String line = "";
        for (int i = s; i <= e; i++) {
            line += words[i];
            if (i == e) break;
            line += nChars(spaces[i - s], ' ');
        }
        res.add(line);
    }

    private String nChars(int n, char c) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += c;
        }
        return res;
    }
}


class SolutionIII {
    // credit: https://leetcode.com/problems/text-justification/discuss/24873/Share-my-concise-c++-solution-less-than-20-lines
    // tag: str
    // time: O(n)
    // space: O(1)
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();

        for (int i = 0, j = 0; i < words.length; i = j + 1) { // i: row start word idx, j: row end word idx
            int l = words[i].length(); // row len
            j = i;
            while (j + 1 < words.length && l + 1 + words[j + 1].length() <= L) {
                l += 1 + words[j + 1].length();
                j++;
            }

            StringBuilder sb = new StringBuilder(); // curr line
            sb.append(words[i]);

            if (i == j) { // single word
                int spaces = L - sb.length();
                while (spaces-- > 0) sb.append(' ');
                res.add(sb.toString());
                continue;
            }

            if (j == words.length - 1) { // last line
                for (int k = i + 1; k <= j; k++) {
                    sb.append(" " + words[k]);
                }
                int spaces = L - sb.length();
                while (spaces-- > 0) sb.append(' ');
                res.add(sb.toString());
                continue;
            }

            // multiple words (normal case)
            int spaces = (L - l + j - i) / (j - i);
            int extras = (L - l + j - i) % (j - i);
            for (int k = i + 1; k <= j; k++) {
                for (int s = spaces; s > 0; s--) sb.append(' ');
                if (extras-- > 0) sb.append(' ');
                sb.append(words[k]);
            }
            res.add(sb.toString());
        }
        return res;
    }
}
