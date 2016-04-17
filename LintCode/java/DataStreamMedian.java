import java.util.Comparator;
import java.util.PriorityQueue;

public class DataStreamMedian {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */


    // V1, O(nlogn)
    // PriorityQueue(Heap)
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        }); // default to min heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int[] result = new int[nums.length];
        int median = nums[0];
        result[0] = median;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < median) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(median);
                median = maxHeap.poll();
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.add(median);
                median = minHeap.poll();
            }

            result[i] = median;
        }

        return result;
    }
}
