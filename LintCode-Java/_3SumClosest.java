import java.util.Arrays;

public class _3SumClosest {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */

    // V1, O(n^2), O(1)
    // Two pointers
    public int threeSumClosest(int[] numbers ,int target) {
        if (numbers == null || numbers.length < 3) {
            return 0;
        }

        Arrays.sort(numbers);

        int closestSum = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length - 2; i++) {
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }

                closestSum = Math.abs(sum - target) < Math.abs(closestSum - target) ? sum : closestSum;
            }
        }

        return closestSum;
    }
}


