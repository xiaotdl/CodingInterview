package find_eventual_safe_states;

import java.util.*;

/**
 * Created by Xiaotian on 3/17/18.
 */
public class Solution {
    // reverse topological sort
    // tag: topological sort
    // time: O(n)
    // space: O(n)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans=new ArrayList<>();
        List<List<Integer>> in=new ArrayList<>();
        Queue<Integer> q=new ArrayDeque<>();
        int n=graph.length;
        int[] out=new int[n];
        for (int i=0;i<n;i++) in.add(new ArrayList<>());
        for (int i=0;i<n;i++)
        {
            out[i]=graph[i].length;
            for (int j:graph[i])
            {
                in.get(j).add(i);
            }
        }
        for (int i=0;i<n;i++)
            if (out[i]==0) q.add(i);
        while (!q.isEmpty())
        {
            int x=q.poll();
            ans.add(x);
            for (int y:in.get(x))
            {
                out[y]--;
                if (out[y]==0) q.add(y);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
