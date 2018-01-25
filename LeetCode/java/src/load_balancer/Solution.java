package load_balancer;

import java.util.*;

/**
 * Created by Xiaotian on 1/24/18.
 */
public class Solution {
}

class LoadBalancer {
    Map<Integer, Integer> svrId2Idx;
    List<Integer> servers;
    Random rand;

    public LoadBalancer() {
        svrId2Idx = new HashMap<>();
        servers = new ArrayList<>();
        rand = new Random();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        svrId2Idx.put(server_id, servers.size());
        servers.add(server_id);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        // swap curr and tail server in list and update new index in dict
        int currIdx = svrId2Idx.get(server_id);
        int tail = servers.size() - 1;
        servers.set(currIdx, servers.get(tail));
        svrId2Idx.put(servers.get(tail), currIdx);
        servers.remove(tail);
        svrId2Idx.remove(server_id);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        return servers.get(rand.nextInt(servers.size()));
    }
}
