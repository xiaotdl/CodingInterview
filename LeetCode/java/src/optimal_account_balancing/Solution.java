package optimal_account_balancing;

import java.util.*;

/**
 * Created by Xiaotian on 2/21/18.
 */
public class Solution {
    // Ref: https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649457159&idx=1&sn=288df3eceffd60138bcd85f4b14b625a&chksm=887eec0fbf096519442d0881943da2c51d0bc4b4ae88056c903590b5031cd7206badfcffb601&mpshare=1&scene=1&srcid=051663ZcWLVR7mjjeLFEctt7&key=316336ac2ae675e0032894aedd960d8ceea1f051ac303491b9e303ffc572b681b35171571e8ec061c0752bb6796282429def74b617f429d0549b52f7199be8d0bf303c17edb2e3d7cfa35aa5e4527296&ascene=0&uin=MTUyMzg3NjAwMA%253D%253D&devicetype=iMac+MacBookAir7%252C1+OSX+OSX+10.12.4+build(16E195)&version=12020610&nettype=WIFI&fontScale=100&pass_ticket=2efBcNTN101DEbwElCImeltuLm3URVi%252FjDBa%252FjonW833w6wEk0mIZ8NIVY86ekDD
    // tag: graph, dp
    // time: O(4^n)
    // space: O(2^n)
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> debt = new HashMap<>();
        for(int[] t : transactions){           //预处理收支情况
            debt.put(t[0], debt.getOrDefault(t[0], 0) - t[2]);
            debt.put(t[1], debt.getOrDefault(t[1], 0) + t[2]);
        }
        int[] account = new int[debt.size()];
        int n = 0;                           //去除收支平衡的人后的总人数
        for(int v : debt.values()){
            if(v != 0){
                account[n++] = v;
            }
        }

        if(n == 0)
            return 0;

        // dp[i]: 当前子集的最优解
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        for(int i = 1; i <  dp.length; i++){   //枚举每个子集
            int sum = 0;
            int count = 0;
            for(int j = 0; j < n; j++){
                if(((1<<j) & i) != 0){         //这个子集里有第j个人
                    sum += account[j];         //加上他的收支情况
                    count++;                   //平衡这个子集需要的最大交易数
                }
            }

            if(sum == 0){                      //如果这个子集的收支平衡，那么它是一个子问题
                dp[i] = count - 1;             //这个子集需要的最大交易数
                for(int j = 1; j < i; j++){    //枚举这个子问题的子集
                    if(((i & j) == j) && dp[j] + dp[i-j] < dp[i]){
                        dp[i] = dp[j] + dp[i - j];  //求这个子问题的最优解
                    }
                }
            }
        }
        return dp[dp.length - 1];              //返回总问题的最优解
    }
}
