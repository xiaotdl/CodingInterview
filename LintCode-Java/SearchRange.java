import java.util.ArrayList;
import java.util.Arrays;

public class SearchRange {
    /**
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */

    // V1, O(logn)
    // Iteration, Binary Search
    public static ArrayList<Integer> searchRange(ArrayList<Integer> A, int target) {
        ArrayList<Integer> result = new ArrayList<Integer>(2);
        result.add(-1);
        result.add(-1);
        int start, end, mid;

        if (A == null || A.size() == 0) {
            return result;
        }

        //find left bound
        start = 0;
        end = A.size() - 1;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A.get(mid) == target) {
                end = mid;
            } else if (A.get(mid) > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A.get(start) == target) {
            result.set(0, start);
        } else if (A.get(end) == target) {
            result.set(0, end);
        } else {
            return result;
        }

        //find right bound
        start = 0;
        end = A.size() - 1;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A.get(mid) == target) {
                start = mid;
            } else if (A.get(mid) > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A.get(end) == target) {
            result.set(1, end);
        } else if (A.get(start) == target) {
            result.set(1, start);
        } else {
            return result;
        }

        return result;
    }

    // V2, O(n), just for fun
    // Iteration, Binary Search
    public static ArrayList<Integer> searchRange_2(ArrayList<Integer> A, int target) {
        ArrayList<Integer> result = new ArrayList<Integer>(2);
        result.add(-1);
        result.add(-1);
        int start, end, mid;
        int low, high;

        if (A == null || A.size() == 0) {
            return result;
        }

        //find left bound
        start = 0;
        end = A.size() - 1;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A.get(mid) == target) {
                end = mid;
            } else if (A.get(mid) > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A.get(start) == target) {
            low = start;
        } else if (A.get(end) == target) {
            low = end;
        } else {
            return result;
        }

        //find right bound
        high = low + 1;
        while (high < A.size() && A.get(high).equals(A.get(low))) {
            high++;
        }

        result.set(0, low);
        result.set(1, high - 1);
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 3, 9, 11));
        System.out.println(searchRange(arr, 3));
        System.out.println(searchRange_2(arr, 3));
    }
}
