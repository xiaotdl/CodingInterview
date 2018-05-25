package accounts_merge;

import java.util.*;

/**
 * Created by Xiaotian on 5/1/18.
 */
class UnionFindSet {
    int parents[];
    UnionFindSet(int n){
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }
}

class Solution {
    // credit: https://leetcode.com/problems/accounts-merge/solution/
    // tag: union find
    // time: O(nlogn), n: total emails
    // space: O(n)
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> email2id = new HashMap<>();
        Map<String, String> email2name = new HashMap<>();
        int id = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (email2id.containsKey(email)) continue;
                email2name.put(email, name);
                email2id.put(email, id++);
            }
        }

        int emailCnt = id;
        UnionFindSet ufs = new UnionFindSet(emailCnt);
        for (List<String> account : accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                String email = account.get(i);
                ufs.union(email2id.get(firstEmail), email2id.get(email));
            }
        }

        Map<Integer, Set<String>> map = new HashMap(); // rootEmailId2unionedEmails
        for (String email : email2name.keySet()) {
            int emailId = email2id.get(email);
            int rootEmailId = ufs.find(emailId);
            if (!map.containsKey(rootEmailId)) {
                map.put(rootEmailId, new HashSet<>());
            }
            map.get(rootEmailId).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> e : map.entrySet()) {
            int emailId = e.getKey();
            Set<String> emails = e.getValue();
            List<String> sortedEmails = new ArrayList<>(emails);
            Collections.sort(sortedEmails);
            String name = email2name.get(sortedEmails.get(0));
            sortedEmails.add(0, name);
            res.add(sortedEmails);
        }
        return res;
    }
}
