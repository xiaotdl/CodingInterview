// tag: str, ptr
// time: O(n)
// space: O(n)
class Solution {
public:
    string convert(string s, int numRows) {
        vector<string> rowStrs(numRows, "");
        int i = 0;
        while (i < s.size()) {
            for (int r = 0; r < numRows && i < s.size(); ++r) {
                rowStrs[r].push_back(s[i++]);
            }
            for (int r = numRows - 2; r >= 1 && i < s.size(); --r) {
                rowStrs[r].push_back(s[i++]);
            }
        }
        string result;
        for (auto rowStr : rowStrs) {
            result.append(rowStr);
        }
        return result;
    }
};
