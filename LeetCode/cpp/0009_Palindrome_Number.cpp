// tag: math
// time: O(n)
// space: O(n)
class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        vector<int> v;
        while (x > 0) {
            int digit = x % 10;
            v.insert(v.begin(), digit);
            x /= 10;
        }
        for (int l = 0, r = v.size() - 1; l < v.size() && r >= 0 && l < r; ++l, --r) {
            if (v[l] == v[r]) continue;
            return false;
        }
        return true;
    }
};

// tag: math, 数位分离
// time: O(n)
// space: O(1)
class Solution {
public:
    int reversed(int x) {
        int reversed = 0;
        while (x > 0) {
            int digit = x % 10;
            reversed = 10 * reversed + digit;
            x /= 10;
        }
        return reversed;
    }

    bool isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        return x == reversed(x);
    }
};
