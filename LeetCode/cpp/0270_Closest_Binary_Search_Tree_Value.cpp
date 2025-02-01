/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
// tag: binary search, recursive
// time: O(logn)
// space: O(1)
class Solution {
public:
    int closestValue(TreeNode* root, double target) {
        if (!root) return -1;

        double minDiff = std::numeric_limits<double>::max(); // XXX
        int result = -1;
        dfs(root, target, minDiff, result);
        return result;
    }

    void dfs(TreeNode* node, double target, double& minDiff, int& result) {
        if (!node) return;
        if (node->val == target) {
            result = target;
            minDiff = 0;
            return;
        }

        double diff = abs(node->val - target);
        if (diff < minDiff) {
            result = node->val;
            minDiff = diff;
        } else if (diff == minDiff) {
            result = min(result, node->val);
        }
        if (node->val < target) {
            dfs(node->right, target, minDiff, result);
        } else {
            dfs(node->left, target, minDiff, result);
        }
    }
};
