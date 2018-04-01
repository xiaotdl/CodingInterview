package employee_importance;

import java.util.*;

/**
 * Created by Xiaotian on 3/31/18.
 */
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};

class Solution {
    // tag: graph, bfs
    // time: O(n)
    // space: O(n)
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> m = new HashMap<>(); // employee2importance
        for (Employee e : employees) {
            int u = e.id;
            graph.put(u, new HashSet<>());
            for (int v : e.subordinates) {
                graph.get(u).add(v);
            }
            m.put(u, e.importance);
        }

        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        while (!q.isEmpty()) {
            int u = q.poll();
            res += m.get(u);
            for (int v : graph.get(u)) {
                q.offer(v);
            }
        }
        return res;
    }
}
