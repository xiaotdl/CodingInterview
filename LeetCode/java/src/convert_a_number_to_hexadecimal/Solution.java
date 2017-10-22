package convert_a_number_to_hexadecimal;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public String toHex(int num) {
        if(num == 0) return "0";
        String res = "";
        while(num != 0){
            res = map[(num & 15)] + res;
            num = (num >>> 4);
        }
        return res;
    }
}
