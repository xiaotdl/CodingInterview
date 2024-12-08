// str preprocessing:
// - remove leading/trailing whitespaces
// - handle plus or minus sign
// - out of range case/overflow
// tag: str, ptr, math
// time: O(n)
// space: O(1)
class Solution {
public:
    int myAtoi(string s) {
        if (s.size() == 0) return 0;

        int l = 0;
        int r = s.size() - 1;
        
        // strip whitespaces
        while (s[l] == ' ') l++;
        while (s[r] == ' ') r--;
        
        // handle sign
        int sign = 1; // 1: +, -1: -
        if (s[l] == '+') {
            l++;
        } else if (s[l] == '-') {
            l++;
            sign = -1;
        }

        long num = 0;
        for (int i = l; i <= r; ++i) {
            if (!isdigit(s[i])) break;
            num = 10 * num + static_cast<int>(s[i] - '0');
            if (sign * num < std::numeric_limits<int>::min()) return std::numeric_limits<int>::min();
            if (sign * num > std::numeric_limits<int>::max()) return std::numeric_limits<int>::max();
        }
        return sign * static_cast<int>(num);
    }
};
