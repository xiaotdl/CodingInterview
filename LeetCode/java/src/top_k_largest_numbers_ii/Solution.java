package top_k_largest_numbers_ii;

import java.util.*;

/**
 * Created by Xiaotian on 4/2/18.
 */
class Solution {
    int k;
    PriorityQueue<Integer> pq; // minHeap
    public Solution(int k) {
        this.k = k;
        pq = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        if (pq.size() < k) {
            pq.offer(num);
            return;
        }
        if (num > pq.peek()) {
            pq.poll();
            pq.offer(num);
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        List<Integer> res = new ArrayList<>();
        Iterator it = pq.iterator();
        while (it.hasNext()) res.add((Integer) it.next());
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
}
