package top_k_frequent_words_map_reduce;

import java.util.*;

/**
 * Created by Xiaotian on 4/2/18.
 */
public class Solution {
}

class OutputCollector<K, V> {
    public void collect(K key, V value){};
    // Adds a key/value pair to the output buffer
}

class Document {
    public int id;
    public String content;
}

class Pair {
    String k;
    int v;
    Pair(String k, int v) {
        this.k = k;
        this.v = v;
    }
}

class TopKFrequentWords {
    public static class Map {
        public void map(String x, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int id = value.id;
            String content = value.content;
            int i = 0;
            while (i < content.length()) {
                while (i < content.length() && content.charAt(i) == ' ') i++;
                StringBuilder keySB = new StringBuilder();
                while (i < content.length() && content.charAt(i) != ' ') {
                    keySB.append(content.charAt(i));
                    i++;
                }
                output.collect(keySB.toString(), 1);
            }
        }
    }

    public static class Reduce {
        int k;
        PriorityQueue<Pair> pq; // min heap

        Comparator<Pair> pairCmp = new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.v != p2.v) return p1.v - p2.v;
                return p2.k.compareTo(p1.k); // reverse lexi
            }
        };

        public void setup(int k) {
            this.k = k;
            pq = new PriorityQueue<Pair>(k, pairCmp);
        }

        public void reduce(String key, Iterator<Integer> values) {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }

            Pair p = new Pair(key, sum);
            if (pq.size() < k) {
                pq.offer(p);
            }
            else {
                if (pairCmp.compare(p, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(p);
                }
            }

        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Pair> res = new ArrayList<>();
            while (!pq.isEmpty()) {
                res.add(pq.poll());
            }
            for (int i = k - 1; i >= 0; i--) {
                Pair p = res.get(i);
                output.collect(p.k, p.v);
            }
        }
    }
}
