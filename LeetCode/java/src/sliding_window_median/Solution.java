package sliding_window_median;

import java.util.*;

/**
 * Created by Xiaotian on 9/20/17.
 */
public class Solution {
    // lintcode version
    // tag: heap
    // time:
    //   addNode: O(logn)
    //   removeNode: O(n)
    //   findMedian: O(1)
    // space: O(n)
    class Node implements Comparable<Node>{
        int id, val;

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        public int compareTo(Node other) {
            return this.val != other.val ? this.val - other.val
                                         : this.id - other.id;
        }
    }

    class MedianFinder {
        TreeSet<Node> maxHeap; // size: x or x + 1
        TreeSet<Node> minHeap; // size: x

        MedianFinder() {
            maxHeap = new TreeSet<>();
            minHeap = new TreeSet<>();
        }

        public void addNode(Node node) {
            maxHeap.add(node);
            Node last = maxHeap.last();
            maxHeap.remove(last);
            minHeap.add(last);
            if (minHeap.size() > maxHeap.size()) {
                Node first = minHeap.first();
                minHeap.remove(first);
                maxHeap.add(first);
            }
        }

        public void removeNode(Node node) {
            if (maxHeap.contains(node)) {
                maxHeap.remove(node);
                if (minHeap.size() > maxHeap.size()) {
                    Node first = minHeap.first();
                    minHeap.remove(first);
                    maxHeap.add(first);
                }
            }
            else {
                minHeap.remove(node);
                if (minHeap.size() + 1 < maxHeap.size()) {
                    Node last = maxHeap.last();
                    maxHeap.remove(last);
                    minHeap.add(last);
                }
            }
        }

        public int getMedian() {
            return maxHeap.last().val;
        }
    }
    /*
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        MedianFinder mf = new MedianFinder();

        for (int i = 0; i < k; i++) {
            mf.addNode(new Node(i, nums[i]));
        }
        res.add(mf.getMedian());

        int l, r;
        for (l = 0, r = k; r < nums.length; l++, r++) {
            mf.removeNode(new Node(l, nums[l]));
            mf.addNode(new Node(r, nums[r]));
            res.add(mf.getMedian());
        }
        return res;
    }
}

class SolutionII {
    // Same as Solution
    // leetcode version
    class Node implements Comparable<Node>{
        int id;
        double val;

        public Node(int id, double val) {
            this.id = id;
            this.val = val;
        }

        public int compareTo(Node other) {
            if (this.val == other.val) {
                return this.id - other.id;
            }
            else if (this.val > other.val) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    class MedianFinder {
        TreeSet<Node> maxHeap;
        TreeSet<Node> minHeap;

        MedianFinder() {
            maxHeap = new TreeSet<>(); // size: x || x+1
            minHeap = new TreeSet<>(); // size: x
        }

        public void addNode(Node node) {
            maxHeap.add(node);
            Node last = maxHeap.last();
            maxHeap.remove(last);
            minHeap.add(last);
            if (minHeap.size() > maxHeap.size()) {
                Node first = minHeap.first();
                minHeap.remove(first);
                maxHeap.add(first);
            }
        }

        public void removeNode(Node node) {
            if (maxHeap.contains(node)) {
                maxHeap.remove(node);
                if (maxHeap.size() < minHeap.size()) {
                    Node first = minHeap.first();
                    minHeap.remove(first);
                    maxHeap.add(first);
                }
            }
            else {
                minHeap.remove(node);
                if (minHeap.size() + 1 < maxHeap.size()) {
                    Node last = maxHeap.last();
                    maxHeap.remove(last);
                    minHeap.add(last);
                }
            }
        }

        public double getMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return ((double) maxHeap.last().val + (double) minHeap.first().val) / 2.0;
            }
            return (double) maxHeap.last().val;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        if (nums == null || nums.length == 0) return res;

        MedianFinder mf = new MedianFinder();

        for (int i = 0; i < k; i++) {
            mf.addNode(new Node(i, nums[i]));
        }
        res[0] = mf.getMedian();

        int l, r;
        for (l = 0, r = k; r < nums.length; l++, r++) {
            mf.removeNode(new Node(l, nums[l]));
            mf.addNode(new Node(r, nums[r]));
            res[l + 1] = mf.getMedian();
        }
        return res;
    }
}

