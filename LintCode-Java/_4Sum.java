import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class _4Sum {
    /**
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *           zero.
     */

    // V1, O(n^3), O(1)
    // Two pointers
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numbers == null || numbers.length < 4) {
            return result;
        }

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 3; i++) {
            if (i != 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if (j != i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = numbers.length - 1;
                while (left < right) {
                    int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
                    if (sum == target) {
                        ArrayList<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(numbers[i]);
                        tmp.add(numbers[j]);
                        tmp.add(numbers[left]);
                        tmp.add(numbers[right]);
                        result.add(tmp);
                        left++;
                        right--;
                        while (left < right && numbers[left] == numbers[left - 1]) {
                            left++;
                        }
                        while (left < right && numbers[right] == numbers[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    // V2, O(n^2 * k), number of k sum pairs
    // Hash
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numbers == null || numbers.length < 4) {
            return result;
        }

        HashMap<Integer, ArrayList<ArrayList<Integer>>> pairs = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                ArrayList<Integer> pair = new ArrayList<Integer>(2);
                pair.add(i);
                pair.add(j);
                int sum = numbers[i] + numbers[j];
                if (!pairs.containsKey(sum)) {
                    ArrayList<ArrayList<Integer>> sumPairs = new ArrayList<>();
                    sumPairs.add(pair);
                    pairs.put(sum, sumPairs);
                } else {
                    pairs.get(sum).add(pair);
                }
            }
        }

        for (int i = 0; i < numbers.length - 3; i++) {
            if (i != 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if (j != i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }

                if (pairs.containsKey(target - numbers[i] - numbers[j])) {
                    ArrayList<ArrayList<Integer>> secondPairs = pairs.get(target - numbers[i] - numbers[j]);
                    Boolean isFirstPush = true;
                    for (int k = 0; k < secondPairs.size(); k++) {
                        // Make sure the indexes are increasing.
                        if (secondPairs.get(k).get(0) <= j) {
                            continue;
                        }
                        // Add to result at first time, or when the third number is different from the one in last result.
                        if (isFirstPush || result.get(result.size() - 1).get(2) != numbers[secondPairs.get(k).get(0)]) {
                            ArrayList<Integer> res = new ArrayList<Integer>();
                            res.add(numbers[i]);
                            res.add(numbers[j]);
                            res.add(numbers[secondPairs.get(k).get(0)]);
                            res.add(numbers[secondPairs.get(k).get(1)]);
                            result.add(res);
                            isFirstPush = false;
                        }
                    }
                }
            }
        }

        return result;
    }
}


