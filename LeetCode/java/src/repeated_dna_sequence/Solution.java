package repeated_dna_sequence;

import java.util.*;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // tag: hash,
    // time: O(n)
    // space: O(1)
    /**
     * @param s: a string represent DNA sequences
     * @return: all the 10-letter-long sequences
     */
    public List<String> findRepeatedDna(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String curr = s.substring(i, i + 10);
            if (!seen.contains(curr)) {
                seen.add(curr);
            }
            else {
                repeated.add(curr);
            }
        }
        return new ArrayList(repeated);
    }
}

class SolutionII {
    // rolling hash: https://en.wikipedia.org/wiki/Rolling_hash
    // save a checksum(32 bit int) instead of a 10 chars string (10 * 16bits) to save some space
    // checksum用4进制来算
    // tag: hash, bit
    // time: O(n)
    // space: O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i = 9; i < s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            int checksum = generateChecksum(subStr);
            if (seen.contains(checksum)) {
                repeated.add(subStr);
            } else {
                seen.add(checksum);
            }
        }
        return new ArrayList<String>(repeated);
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

class SolutionIII {
    // rolling hash: https://en.wikipedia.org/wiki/Rolling_hash
    // tag: hash, bit
    // time: O(n)
    // space: O(n)
    private static final int BASE = 4;
    private static final int MOD = (int) Math.pow(BASE, 10);
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 10) return res;

        char[] S = s.toCharArray();

        Map<Character, Integer> C = new HashMap<>(); // char2hashCode
        C.put('A', 0); C.put('C', 1); C.put('G', 2); C.put('T', 3);

        Set<Long> seen = new HashSet<>(); // hashCodes
        Set<String> repeated = new HashSet<>(); // sequences
        long hashCode = 0; // rolling hash
        for (int i = 0; i < 10; i++) {
            hashCode = hashCode * BASE + C.get(S[i]);
        }
        seen.add(hashCode);

        for (int i = 1, j = 10; j < S.length; i++, j++) {
            hashCode = (hashCode * BASE) % MOD + C.get(S[j]);
            if (seen.contains(hashCode)) {
                repeated.add(s.substring(i, j + 1));
                continue;
            }
            seen.add(hashCode);
        }
        return new ArrayList<String>(repeated);
    }
}

