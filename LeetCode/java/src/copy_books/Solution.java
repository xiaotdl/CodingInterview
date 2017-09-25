package copy_books;

/**
 * Created by Xiaotian on 9/25/17.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogm), n: book num, m: total pages
    // space: O(1)
    /*
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0 || k < 0) return 0;

        int totalPages = 0;
        int maxPagesPerBook = 0;
        for (int pagesPerBook : pages) {
            totalPages += pagesPerBook;
            maxPagesPerBook = Math.max(maxPagesPerBook, pagesPerBook);
        }

        int l = maxPagesPerBook;
        int r = totalPages;
        while (l + 1 < r) {
            int m = l + (r - l) / 2; // find max pages per person
            if (personCnt(pages, m) <= k) {
                r = m;
            } else {
                l = m;
            }
        }
        if (personCnt(pages, l) <= k) return l;
        if (personCnt(pages, r) <= k) return r;
        return -1;
    }

    private int personCnt(int[] pages, int maxPagesPerPerson) {
        int cnt = 1;
        int sum = pages[0];
        for (int i = 1; i < pages.length; i++) {
            if (sum + pages[i] > maxPagesPerPerson) {
                cnt++;
                sum = 0;
            }
            sum += pages[i];
        }
        return cnt;
    }
}
