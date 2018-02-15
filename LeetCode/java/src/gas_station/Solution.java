package gas_station;

/**
 * Created by Xiaotian on 2/13/18.
 */
public class Solution {
    // tag: array, greedy
    // time: O(n)
    // space: O(n)
    /**
     * @param gas: An array of integers
     * @param cost: An array of integers
     * @return: An integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) return -1;

        int n = gas.length;
        int sumGas = 0;
        int sumCost = 0;
        for (int i = 0; i < n; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
        }
        if (sumGas < sumCost) {
            return -1;
        }

        int start = 0;
        int tank = 0; // prev gas in tank
        for (int i = 0; i < n; i++) {
            if (tank + gas[i] < cost[i]) {
                start = i + 1;
                tank = 0;
            }
            else {
                tank += gas[i] - cost[i];
            }
        }
        return start;
    }
}
