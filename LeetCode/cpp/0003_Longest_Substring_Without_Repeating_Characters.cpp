// tag: brutal force
// time: O(n^2)
// space: O(n)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        size_t maxLen = 0;
        // enumerate all substrings
        for (int i = 0; i < s.size(); ++i) { // start index of the substring
            set<char> seen;
            for (int j = i; j < s.size(); ++j) { // end index of the substring
                if (seen.contains(s[j])) {
                    maxLen = std::max(maxLen, seen.size());
                    break;
                }
                seen.emplace(s[j]);
            }
        }
        return maxLen;
    }
};


// tag: str, hash table, sliding window
// time: O(n), at most 2n steps for i and j
// space: O(1)
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        size_t maxLen = 0;
        set<char> seen;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            while (j < s.size()) {
                if (seen.contains(s[j])) {
                    maxLen = std::max(maxLen, seen.size());
                    break;
                } else {
                    seen.emplace(s[j]);
                    ++j;
                }
            }
            seen.erase(s[i]);
        }
        return maxLen;
    }
};
