package course_schedule_ii;

import java.util.*;

/**
 * Created by Xiaotian on 2/5/18.
 */
public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            edges.put(i, new HashSet<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int to = prerequisites[i][0];
            int from = prerequisites[i][1]; // prerequisite course
            if (!edges.get(from).contains(to)) { // in case duplicate prerequisites
                indegrees[to]++;
            }
            edges.get(from).add(to);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }

        int[] res = new int[numCourses];

        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++){
                int course = q.poll();
                res[count] = course;
                count++;

                for (Integer to : edges.get(course)) {
                    indegrees[to]--;
                    if (indegrees[to] == 0) {
                        q.offer(to);
                    }
                }
            }
        }

         return count == numCourses ? res : new int[0];
    }
}
