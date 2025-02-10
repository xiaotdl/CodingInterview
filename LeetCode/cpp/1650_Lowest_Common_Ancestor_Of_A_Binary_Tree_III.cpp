// tag: brutal force
// time: O(Height)
// space: O(Height)
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* parent;
};
*/

class Solution {
public:
    Node* lowestCommonAncestor(Node* p, Node * q) {
        std::unordered_set<Node*> ancestors;
        while (p) {
            ancestors.insert(p);
            p = p->parent;
        }
        while (q) {
            if (ancestors.contains(q)) {
                return q;
            }
            q = q->parent;
        }
        return nullptr;
    }
};
