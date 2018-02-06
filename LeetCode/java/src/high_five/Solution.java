package high_five;

import java.util.*;

/**
 * Created by Xiaotian on 2/5/18.
 */
class Record {
    public int id, score;
    public Record(int id, int score){
        this.id = id;
        this.score = score;
    }
}

public class Solution {
    // tag: heap
    // time: O(n)
    // space: O(k)
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, Double> res = new HashMap<>();

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(); // id2top5scores

        for (Record record : results) {
            int id = record.id;
            int score = record.score;
            map.putIfAbsent(id, new PriorityQueue<Integer>()); // min heap
            PriorityQueue<Integer> pq = map.get(id);
            pq.add(score);
            if (pq.size() > 5) {
                pq.poll();
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> e : map.entrySet()) {
            int id = e.getKey();
            PriorityQueue<Integer> scores = e.getValue();
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += scores.poll();
            }
            double avg = sum / 5.0;
            res.put(id, avg);
        }
        return res;
    }
}
