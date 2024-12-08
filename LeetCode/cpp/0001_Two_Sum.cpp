// tag: brutal force
// time: O(n^2)
// space: O(1)
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        for (int i = 0; i < nums.size(); ++i) {
            for (int j = i + 1; j < nums.size(); ++j) {
                if (nums[i] + nums[j] == target) {
                    return {i, j};
                }
            }
        }
        return {};
    }
};

// tag: hash table
// time: O(n)
// space: O(n)
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        std::map<int, int> prevValues; // {key = prev value, val = index}
        for (int i = 0; i < nums.size(); ++i) {
            if (auto found = prevValues.find(target - nums[i]); found != prevValues.end()) {
                int j = found->second;
                return {j, i};
            }
            prevValues[nums[i]] = i;
        }
        return {};
    }
};
