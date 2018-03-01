解决BST类问题要从BST的性质着手
画出来从小到大、类似二分查找，经常与root比较后分类讨论

扩展：BST与二分法的关系
- 二分法(O(logn)查找)
- BST(O(logn)增加，删除，查找, worst case O(n))
- 平衡BST(always O(logn)增删查)


== Inorder predecessor/successor in BST ==
predecessor:
    1) right most node in left subtree
    2) parent from top-left
    3) null
successor:
    1) left most node in right subtree
    2) parent from top-right
    3) null
e.g.
                25
              /    \
            15      40
           /  \    /  \
         10   18  35  45

node | predecessor | successor
-----+-------------+----------
25   | 18          | 35
10   | null        | 15 (parent)
45   | 40 (parent) | null
