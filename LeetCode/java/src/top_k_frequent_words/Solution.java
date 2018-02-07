package top_k_frequent_words;

import java.util.*;

/**
 * Created by Xiaotian on 2/6/18.
 */
public class Solution {
    // tag: heap
    // time: O(nlogk)
    // space: O(n)
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() != b.getValue() ? a.getValue() - b.getValue()
                                                   : b.getKey().compareTo(a.getKey()) // reversed lexicographical
        );
        Map<String, Integer> map = new HashMap<>(); //word2cnt

        for (String word : words) {
            map.putIfAbsent(word, 0);
            map.put(word, map.get(word) + 1);
        }

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            minHeap.offer(e);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> e = minHeap.poll();
            String word = e.getKey();
            res.add(0, word);
        }
        return res;
    }
}
