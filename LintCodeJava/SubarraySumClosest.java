import java.util.ArrayList;
import java.util.Arrays;

public class SubarraySumClosest {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */

    // V1, O(nlogn)
    // PrefixSum
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();

        Element[] sums = new Element[nums.length + 1];
        sums[0] = new Element(0, -1); // setting index to -1 is important
        int sum = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            sum += nums[i - 1];
            sums[i] = new Element(sum, i - 1);
        }

        Arrays.sort(sums);

        int min = Math.abs(sums[0].getValue() - sums[1].getValue());
        int start = Math.min(sums[0].getIndex(), sums[1].getIndex()) + 1;
        int end = Math.max(sums[0].getIndex(), sums[1].getIndex());
        for (int i = 1; i < nums.length; i++) {
            int diff = Math.abs(sums[i].getValue() - sums[i + 1].getValue());
            if (diff < min) {
                min = diff;
                start = Math.min(sums[i].getIndex(), sums[i + 1].getIndex()) + 1;
                end = Math.max(sums[i].getIndex(), sums[i + 1].getIndex());
            }
        }

        result.add(start);
        result.add(end);
        return result;
    }
    class Element implements Comparable<Element>{
        int val;
        int index;
        public Element(int v, int i){
            val = v;
            index = i;
        }

        public int compareTo(Element other){
            return this.val - other.val;
        }

        public int getIndex(){
            return index;
        }

        public int getValue(){
            return val;
        }
    }
}
