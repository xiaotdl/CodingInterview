package exclusive_time_of_functions;

import java.util.*;

/**
 * Created by Xiaotian on 3/31/18.
 */
public class Solution {
    // tag: stack
    // time: O(n)
    // space: O(n)
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> callStack = new Stack<>(); // stackFrame: int[]{functionId, startTime}, runTime=[startTime, nextStartTime)

        for (String log : logs) {
            String[] s = log.split(":");
            int functionId = Integer.parseInt(s[0]);
            String start_or_end = s[1];
            int time = Integer.parseInt(s[2]);

            if (start_or_end.equals("start")) { // start log
                int startTime = time;
                if (!callStack.isEmpty()) {
                    int[] callerFrame = callStack.peek();
                    int callerFunctionId = callerFrame[0];
                    int callerFunctionStartTime = callerFrame[1];
                    res[callerFunctionId] += startTime - callerFunctionStartTime;
                }
                callStack.push(new int[]{functionId, startTime});
            }
            else { // end log
                int[] frame = callStack.pop();
                int startTime = frame[1];
                int nextStartTime = time + 1;
                res[functionId] += nextStartTime - startTime;
                if (!callStack.isEmpty()) {
                    int[] callerFrame = callStack.peek();
                    callerFrame[1] = nextStartTime;
                }
            }
        }
        return res;
    }
}
