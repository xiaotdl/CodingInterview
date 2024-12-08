// tag: math, 数位分离
// time: O(n)
// space: O(1)
class Solution {
public:
    int reverse(int x) {
        long res = 0;
        while (x) {
            int digit = x % 10;
            res = 10 * res + digit;
            if (res > std::numeric_limits<int>::max()) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }
};
