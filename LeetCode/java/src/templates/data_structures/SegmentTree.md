Segment Tree < Binary Tree

解决**区间操作**相关问题

什么情况下，无法使用线段树？
如果我们删除或者增加区间中的元素，那么区间的大小将发生变化，此时是无法使用线段树解决这种问题的。

## Methods
- build: O(n), O(n), top-down recursively build root, root.left, root.right
- modify: O(logn), O(1), non/complete/partial-overlap
- query: O(logn), O(1), non/complete/partial-overlap

## Application
- range sum
- range min/max
- range count

## Construction
- index as range
- value as range


// update a single element
    Most common.

// update a range of elements - lazy propagation
    https://www.youtube.com/watch?v=xuoQdt5pHj0

    https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
    Using Lazy Propagation allows us to overcome all of these problems by reducing wasteful computations and processing nodes on-demand.
    We try to postpone updating descendants of a node, until the descendants themselves need to be accessed.

