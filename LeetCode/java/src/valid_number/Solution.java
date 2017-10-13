package valid_number;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // credit: https://discuss.leetcode.com/topic/9490/clear-java-solution-with-ifs
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
}

class SolutionII {
    // regex
    // int: \s+[+-]?\d+(\.)?\s+
    // float: \s+[+-]?(?:\d+|\d*\.\d+)\s+
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0) return false;

//        return s.matches("^\\s*[-+]?(?:[0-9]+(\\.[0-9]*)?|\\.[0-9]+)(e[-+]?[0-9]+)?\\s*$");
        //                                 int     |    float        scientific
        return s.matches("^\\s*[-+]?(?:[0-9]+(\\.)?|[0-9]*\\.[0-9]+)(e[-+]?[0-9]+)?\\s*$");
    }
}

class SolutionIII {
    // Deterministic Finite Automaton

    // Ref:
    // https://www.tutorialspoint.com/automata_theory/deterministic_finite_automaton.htm
    // Formal Definition of a DFA
    // A DFA can be represented by a 5-tuple (Q, ∑, δ, q0, F) where −
    //   - Q is a [finite set of states].
    //   - ∑ is a finite set of symbols called the [alphabet].
    //   - δ is the [transition function] where δ: Q × ∑ → Q
    //   - q0 is the [initial state] from where any input is processed (q0 ∈ Q).
    //   - F is a set of [final state/states] of Q (F ⊆ Q).

    // Ref: convert regex to nfa/dfa
    // http://hackingoff.com/compilers/regular-expression-to-nfa-dfa
    // \s*[-+]?(\d+(\.)?|\d*\.\d+)(e[-+]?\d+)?\s*

    // Ref: test cases
    // https://discuss.leetcode.com/topic/1095/the-worst-problem-i-have-ever-met-in-this-oj/11
    // == common ==
    // prefix/suffix spaces are allowed
    // leading zeroes are allowed

    // == int ==
    // "0" => true

    // == float ==
    // " 0.1 " => true
    // "0." => true

    // == scientific == (eX == 10^X)
    // "2e10" => true
    // "0.5e-10" => true
    // "3e" => false
    // "e9" => false

    // == invalid ==
    // null => false
    // "" => false
    // "   " => false
    // "1  1" => false
    // "abc" => false
    // "1 a" => false

    // Solution:
    // https://discuss.leetcode.com/topic/30058/a-simple-solution-in-python-based-on-dfa
}




