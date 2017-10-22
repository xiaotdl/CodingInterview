package repeated_dna_sequence;

import java.util.*;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // rolling hash: https://en.wikipedia.org/wiki/Rolling_hash
    // save a checksum(32 bit int) instead of a 10 chars string (10 * 16bits) to save some space
    // checksum用4进制来算
    // tag: bit
    // time: O(1)
    // space: O(1)
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> seen = new HashSet<>();
        Set<String> duplicate = new HashSet<>();
        for (int i = 9; i < s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            int checksum = generateChecksum(subStr);
            if (seen.contains(checksum)) {
                duplicate.add(subStr);
            } else {
                seen.add(checksum);
            }
        }
        return new ArrayList<String>(duplicate);
    }

    private int generateChecksum(String s) {
        List<Character> symbols = new ArrayList(Arrays.asList('A', 'C', 'G', 'T'));
        int checksum = 0;
        for (char c : s.toCharArray()) {
            checksum = symbols.size() * checksum + symbols.indexOf(c);
        }
        return checksum;
    }
}
