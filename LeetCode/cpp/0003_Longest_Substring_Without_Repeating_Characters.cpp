// tag: brutal force
// time: O(n^2)
// space: O(n)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        size_t longestSubstringSize = 0;
        // enumerate all substrings
        for (int i = 0; i < s.size(); ++i) { // start index of the substring
            set<char> seen;
            for (int j = i; j < s.size(); ++j) { // end index of the substring
                if (seen.contains(s[j])) {
                    longestSubstringSize = std::max(longestSubstringSize, seen.size());
                    break;
                }
                seen.emplace(s[j]);
            }
        }
        return longestSubstringSize;
    }
};
