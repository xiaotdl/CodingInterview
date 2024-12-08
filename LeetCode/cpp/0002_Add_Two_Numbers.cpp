// tag: linkedlist
// time: O(n)
// space: O(1)
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode;
        ListNode* prev = dummy;
        int carry = 0;
        while (l1 || l2 || carry > 0) {
            int val = 0;
            if (l1) {
                val += l1->val;
                l1 = l1->next;
            }
            if (l2) {
                val += l2->val;
                l2 = l2->next;
            }
            if (carry > 0) {
                val += carry;
                carry = 0;
            }
            if (val >= 10) {
                val %= 10;
                carry = 1;
            }
            ListNode* curr = new ListNode(val);
            prev->next = curr;
            prev = curr;
        }
        return dummy->next;
    }
};
