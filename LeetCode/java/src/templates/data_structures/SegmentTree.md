Segment Tree < Binary Tree

解决**区间操作**相关问题

什么情况下，无法使用线段树？
如果我们删除或者增加区间中的元素，那么区间的大小将发生变化，此时是无法使用线段树解决这种问题的。

## Methods
- build: O(n), O(n)
- modify: O(logn), O(1)
- query: O(logn), O(1)

## Application
- range sum
- range min/max
- range count

## Construction
- index as range
- value as range
