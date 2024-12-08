// tag: brutal force
// time: O(n^2)
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
