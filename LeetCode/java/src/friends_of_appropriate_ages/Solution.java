package friends_of_appropriate_ages;

/**
 * Created by Xiaotian on 4/30/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/friends-of-appropriate-ages/solution/
    // tag: array
    // time: O(120^2 + n)
    // space: O(120)
    public int numFriendRequests(int[] ages) {
        int[] ageCnt = new int[121];
        for (int age : ages) ageCnt[age]++;

        int res = 0; // requestCnt
        for (int ageA = 1; ageA <= 120; ageA++) {
            int cntA = ageCnt[ageA];
            for (int ageB = 1; ageB <= 120; ageB++) {
                int cntB = ageCnt[ageB];

                if (ageB <= 0.5 * ageA + 7) continue;
                if (ageB > ageA) continue;
                if (ageB > 100 && ageA < 100) continue;

                if (ageA != ageB) {
                    res += cntA * cntB;
                }
                else {
                    res += cntA * (cntA - 1);
                }
            }
        }
        return res;
    }
}
