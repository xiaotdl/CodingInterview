package course_schedule;

import java.util.*;

/**
 * Created by Xiaotian on 12/23/17.
 */
public class Solution {
    // bfs vertices that has indegree == 0
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
            int size = edges[course].size();
            for (int i = 0; i < size; i++) {
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

class SolutionII {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        Map<Integer, Set<Integer>> edges = new HashMap<>(); // vertice2connectedVertices
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

        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++){
                int course = q.poll();
                count++;

                for (Integer to : edges.get(course)) {
                    indegrees[to]--;
                    if (indegrees[to] == 0) {
                        q.offer(to);
                    }
                }
            }
        }
        return count == numCourses;
    }
}

class SolutionIII {
    // topological sort
    public boolean canFinish(int n, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());

        int[] indegree = new int[n];
        for (int[] p : prerequisites) {
            int u = p[1];
            int v = p[0];
            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            cnt++;
            for (int v : graph.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) q.offer(v);
            }
        }
        return cnt == n;
    }
}