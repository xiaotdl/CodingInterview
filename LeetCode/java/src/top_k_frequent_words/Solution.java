package top_k_frequent_words;

import java.util.*;

/**
 * Created by Xiaotian on 2/6/18.
 */
class Solution {
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

class SolutionII {
    // Same as Solution
    // NOTE: lower alphabetical order comes first
    //   ==> lower alphabets should be polled later
    //   ==> s2.compareTo(s1)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> m = new HashMap<>(); // word2cnt
        for (String word : words) {
            m.put(word, m.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(k, new Comparator<String>(){ // minHeap
            @Override
            public int compare(String s1, String s2) {
                int cnt1 = m.get(s1);
                int cnt2 = m.get(s2);
                if (cnt1 != cnt2) return cnt1 - cnt2;
                return s2.compareTo(s1);
            }
        });

        for (String word : m.keySet()) {
            pq.offer(word);
            if (pq.size() > k) pq.poll();
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(pq.poll());
        Collections.reverse(res);
        return res;
    }
}
