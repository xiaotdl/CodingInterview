package compare_version_numbers;

/**
 * Created by Xiaotian on 7/5/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public int compareVersion(String version1, String version2) {
        String[] V1 = version1.split("\\.");
        String[] V2 = version2.split("\\.");
        for (int i = 0; i < Math.max(V1.length, V2.length); i++) {
            int v1 = i < V1.length ? Integer.parseInt(V1[i]) : 0;
            int v2 = i < V2.length ? Integer.parseInt(V2[i]) : 0;
            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }
        return 0;
    }
}
