// tag: brutal force
// time: O(n^3), O(n^2) enumerating all substrs, O(n) check isPalindrome.
// space: O(1)
class Solution {
public:
    bool isPalindrome(string_view s) {
        for (int l = 0, r = s.size() - 1; l < r; ++l, --r) {
            if (s[l] != s[r]) return false;
        }
        return true;
    }

    string longestPalindrome(string s) {
        size_t maxLen = 0;
        string longestPalindrome;
        for (int i = 0; i < s.size(); ++i) {
            for (int j = i; j < s.size(); ++j) {
                string currSubstr = s.substr(i, j + 1);
                if (isPalindrome(currSubstr)) {
                    if (currSubstr.size() > maxLen) {
                        longestPalindrome = currSubstr;
                        maxLen = currSubstr.size();
                    }
                }
            }
        }
        return longestPalindrome;
    }
};

// tag: brutal force
// time: O(n^2), O(n^2) enumerating all substrs
// space: O(n^2), O(n^2) cache all palindromes
class Solution {
public:
    std::set<string> palindromes;
    bool isPalindrome(string s) {
        if (palindromes.contains(s)) {
            return true;
        }
        for (int l = 0, r = s.size() - 1; l < r; ++l, --r) {
            if (s[l] != s[r]) return false;
        }
        palindromes.emplace(s);
        return true;
    }

    string longestPalindrome(string s) {
        size_t maxLen = 0;
        string longestPalindrome;
        for (int i = 0; i < s.size(); ++i) {
            for (int j = i; j < s.size(); ++j) {
                string currSubstr = s.substr(i, j + 1);
                if (isPalindrome(currSubstr)) {
                    if (currSubstr.size() > maxLen) {
                        longestPalindrome = currSubstr;
                        maxLen = currSubstr.size();
                    }
                }
            }
        }
        return longestPalindrome;
    }
};
