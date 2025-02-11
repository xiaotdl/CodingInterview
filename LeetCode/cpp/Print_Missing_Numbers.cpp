#include <iostream>
#include <memory>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

string toString(vector<string> v) {
    stringstream ss;
    int i = 0;
    for (const string& s : v) {
        ss << s;
        ++i;
        if (i != v.size()) {
            ss << ',';
        }
    }
    return ss.str();
}

void printMissingNumber(vector<int> v, int N) {
    vector<string> res;
    int i = 0; // index of v
    vector<int> missingNums;
    for (int x = 0; x <= N; ++x) {
        if (i < v.size()) {
            if (x == v[i]) {
                // skip
                // append missing numbers
                if (!missingNums.empty()) {
                    if (missingNums.size() == 1) {
                        res.push_back(to_string(missingNums[0]));
                    } else {
                        stringstream ss;
                        ss << to_string(missingNums[0]);
                        ss << '-';
                        ss << to_string(missingNums[missingNums.size() - 1]);
                        res.push_back(ss.str());
                    }
                    missingNums.clear();
                }
                ++i;
            } else {
                // update missing numbers
                missingNums.push_back(x);
            }
        } else {
            // update missing numbers
            missingNums.push_back(x);
        }
    }
    // append missing numbers
    if (!missingNums.empty()) {
        if (missingNums.size() == 1) {
            res.push_back(to_string(missingNums[0]));
        } else {
            stringstream ss;
            ss << to_string(missingNums[0]);
            ss << '-';
            ss << to_string(missingNums[missingNums.size() - 1]);
            res.push_back(ss.str());
        }
        missingNums.clear();
    }

    cout << toString(res) << endl;

}


int main() {
    // Example:
    // Input: [2, 4, 7, 13, 20]
    // Output: "0,1,3,5,6,8-12,14-19,21-99"
    vector<int> v{2, 4, 7, 13, 20};
    int N = 100;
    printMissingNumber(v, N);

}
