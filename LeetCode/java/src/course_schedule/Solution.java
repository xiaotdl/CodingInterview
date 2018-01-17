package course_schedule;

import java.util.*;

/**
 * Created by Xiaotian on 12/23/17.
 */
public class Solution {
    // tag: topological sort
    // time: O(mn*m/v), v: avg value in A
    // space: O(mn)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; // indegree[i]: indegree of vertice i

        List[] edges = new ArrayList[numCourses]; // edges[i]: edges(outbound arrows) of vertice i
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int to = prerequisites[i][0];
            int from = prerequisites[i][1]; // prerequisite course
            indegree[to]++;
            edges[from].add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            int n = edges[course].size();
            for (int i = 0; i < n; i++) {
                int to = (int) edges[course].get(i);
                indegree[to]--;
                if (indegree[to] == 0) {
                    queue.add(to);
                }
            }
        }

        return count == numCourses;
    }
}
