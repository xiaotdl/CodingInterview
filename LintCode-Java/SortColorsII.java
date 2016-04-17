import java.util.HashMap;

public class SortColorsII {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */

    // V1, O(n), O(k)
    // Hash, two-pass
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < colors.length; i++) {
            if (map.containsKey(colors[i])) {
                map.put(colors[i], map.get(colors[i]) + 1);
            } else {
                map.put(colors[i], 1);
            }
        }

        int index = 0;
        for (int j = 1; j <= k; j++) {
            while (map.containsKey(j)) {
                map.put(j, map.get(j) - 1);
                if (map.get(j) == 0) {
                    map.remove(j);
                }
                colors[index] = j;
                index++;
            }
        }

        return;
    }

    // V2, O(nlogn), O(1)
    // Quick Sort(in place)
    public void sortColors2(int[] colors, int k) {
        if (colors == null) {
            return;
        }

        quickSort(colors, 0, colors.length - 1);
    }
    private void quickSort(int[] colors, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = colors[right];

        int pos = partition(colors, left, right, pivot);

        quickSort(colors, left, pos - 1);
        quickSort(colors, pos + 1, right);
    }
    private int partition(int[] colors, int left, int right, int pivot) {
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && colors[i] < pivot) { i++; };
            while (i < j && colors[j] >= pivot) { j--; };
            if (i < j) {
                swap(colors, i, j);
            }
        }
        swap(colors, i, right);
        return i;
    }
    private void swap(int[] colors, int left, int right) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }
}

