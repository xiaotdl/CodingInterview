package task_scheduler;

import java.util.*;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char task : tasks) {
            cnt[task - 'A']++;
        }
        Arrays.sort(cnt);

        int i = 25;
        while (i >= 0 && cnt[i] == cnt[25]) i--;

        return Math.max(tasks.length, (cnt[25] - 1) * (n + 1) + (25 - i));
    }
}

class SolutionII {
    // tag: array, heap
    // time: O(nlogk), n: number of tasks, k: number of task types
    // space: O(k)
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>(); // task2count
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        // maxHeap
        PriorityQueue<Map.Entry<Character, Integer>> pq =
            new PriorityQueue<>(map.size(),
                new Comparator<Map.Entry<Character, Integer>>(){
                    @Override
                    public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                        return e1.getValue() != e2.getValue() ?
                                    e2.getValue() - e1.getValue()
                                :
                                    e1.getKey() - e2.getKey();
                    }
                });
        pq.addAll(map.entrySet());

        int cnt = 0;
        while (!pq.isEmpty()) {
            List<Map.Entry<Character, Integer>> executedTasks = new ArrayList<>();
            int k = n + 1; // trunk length
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Character, Integer> top = pq.poll();
                top.setValue(top.getValue() - 1);
                executedTasks.add(top); // to be added back to pq after this trunk
                k--;
                cnt++;
            }
            for (Map.Entry<Character, Integer> e : executedTasks) {
                if (e.getValue() > 0) pq.offer(e);
            }
            if (pq.isEmpty()) break;
            cnt += k;
        }
        return cnt;
    }
}
