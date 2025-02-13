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
// - BFS, put node, col into queue at the same time
// - Every left child access col - 1 while right child col + 1
// - This maps node into different col buckets
// - Get col boundary min and max on the fly
// - Retrieve result from cols
// tag: bfs, queue
// time: O(n)
// space: O(n)
class Solution {
public:
    vector<vector<int>> verticalOrder(TreeNode* root) {
        if (!root) return {};
        unordered_map<int, vector<int>> nodeValsByCol;
        queue<pair<TreeNode*, int>> nodeColPairs;
        int minCol = 0;
        int maxCol = 0;
        // BFS
        nodeColPairs.push({root, 0});
        nodeValsByCol[0].push_back(root->val);
        while(!nodeColPairs.empty()) {
            auto [currNode, currCol] = nodeColPairs.front();
            nodeColPairs.pop();
            if (currNode->left) {
                nodeColPairs.push({currNode->left, currCol - 1});
                nodeValsByCol[currCol - 1].push_back(currNode->left->val);
                minCol = min(minCol, currCol - 1);
            }
            if (currNode->right) {
                nodeColPairs.push({currNode->right, currCol + 1});
                nodeValsByCol[currCol + 1].push_back(currNode->right->val);
                maxCol = max(maxCol, currCol + 1);
            }
        }
        vector<vector<int>> res;
        for (int col = minCol; col <= maxCol; ++col) {
            if (nodeValsByCol.contains(col)) {
                res.push_back(nodeValsByCol[col]);
            }
        }
        return res;
    }
};
