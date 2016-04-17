public class SortLetterByCase {
    /**
     *@param chars: The letter array you should sort by Case
     *@return: void
     */

    // V1, O(n), O(1)
    // Two pointers
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }

        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            while (i < j && (int) chars[i] >= 97) { i++; }; // a~z: 97~122
            while (i < j && (int) chars[j] <= 90) { j--; }; // A~Z: 65~90
            if (i < j) {
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
                j--;
            }
        }

        return;
    }
}


