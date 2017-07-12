package design_log_storage_system;

import java.util.*;

/**
 * Created by Xiaotian on 7/12/17.
 */
public class Solution {
}
class LogSystem {

    Map<Integer,String> timestamps = new HashMap<>();
    Map<String, Integer> gra2subStrIndex = new HashMap<String, Integer>() {{
        put("Year", 4);
        put("Month", 7);
        put("Day", 10);
        put("Hour", 13);
        put("Minute", 16);
        put("Second", 19);
    }};

    // time: O(1)
    // space: O(1)
    // timestamp: Year:Month:Day:Hour:Minute:Second, e.g., 2017:01:01:23:59:59
    public void put(int id, String timestamp) {
        timestamps.put(id, timestamp);
    }

    // time: O(n), n: total number of logs
    // space: O(1)
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        int x = gra2subStrIndex.get(gra);
        String _s = s.substring(0, x);
        String _e = e.substring(0, x);
        for (Integer id : timestamps.keySet()) {
            String _t = timestamps.get(id).substring(0, x);
            if (_t.compareTo(_s) >= 0 && _t.compareTo(_e) <= 0 ) {
                res.add(id);
            }
        }
        return res;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
