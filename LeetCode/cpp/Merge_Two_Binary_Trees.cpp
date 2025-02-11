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

// tag: tree, dfs
// time: O(n), n is the number of total tree nodes
// space: O(n), n is the number of total tree nodes
class Solution {
public:
    TreeNode* mergeTrees(TreeNode* root1, TreeNode* root2) {
        auto root = new TreeNode();
        dfs(root, root1, root2);
        return root;
    }
    
    void dfs(TreeNode* root, TreeNode* root1, TreeNode* root2) {
        if (root1 == nullptr && root2 == nullptr) return;

        if (root1) {
            root->val += root1->val;
        }
        if (root2) {
            root->val += root2->val;
        }
        if ((root1 && root1->left) || (root2 && root2->left)) {
            root->left = new TreeNode();
            dfs(root->left, root1->left, root2->left);
        }
        if ((root1 && root1->right) || (root2 && root2->right)) {
            root->right = new TreeNode();
            dfs(root->right, root1->right, root2->right);
        }
    }
};
