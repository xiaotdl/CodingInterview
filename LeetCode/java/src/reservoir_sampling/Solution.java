package reservoir_sampling;

/**
 * Created by Xiaotian on 3/14/18.
 */

// Similar to shuffle_an_array

// https://en.wikipedia.org/wiki/Reservoir_sampling#Relation_to_Fisher-Yates_shuffle

// Relation to Fisher-Yates shuffle
// Suppose one wanted to draw k random cards from a deck of playing cards (i.e., n=52). A natural approach would be to shuffle the deck and then take the top k cards. In the general case, the shuffle also needs to work even if the number of cards in the deck is not known in advance, a condition which is satisfied by the inside-out version of the Fisher-Yates shuffle:

//   To initialize an array a of n elements to a randomly shuffled copy of S, both 0-based:
//    a[0] ← S[0]
//    for i from 1 to n - 1 do
//        r ← random (0 .. i)
//        a[i] ← a[r]
//        a[r] ← S[i]
// Note that although the rest of the cards are shuffled, only the top k are important in the present context. Therefore, the array a need only track the cards in the top k positions while performing the shuffle, reducing the amount of memory needed. Truncating a to length k, the algorithm is modified accordingly:

//   To initialize an array a to k random elements of S (which is of length n), both 0-based:
//    a[0] ← S[0]
//    for i from 1 to k - 1 do
//        r ← random (0 .. i)
//        a[i] ← a[r]
//        a[r] ← S[i]
//    for i from k to n - 1 do
//        r ← random (0 .. i)
//        if (r < k) then a[r] ← S[i]
// Since the order of the first k cards is immaterial, the first loop can be removed and a can be initialized to be the first k items of S. This yields Algorithm R.

// An efficient Java program to randomly
// select k items from a stream of items
import java.util.*;
import java.util.concurrent.*;

public class Solution {
    // Ref: https://www.geeksforgeeks.org/reservoir-sampling/
    // Algo:
    // 1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
    // 2) Now one by one consider all items from (k+1)th item to nth item.
    //    a) Generate a random number from 0 to i where i is index of current item in stream[]. Let the generated random number is j.
    //    b) If j is in range 0 to k-1, replace reservoir[j] with arr[i]

    // Proof:
    // prove that the probability that any item stream[i] where 0 <= i < n will be in final reservoir[] is k/n
    // Case 1: For last n-k stream items, i.e., for stream[i] where k <= i < n
    //     1) let us first consider the last item. The probability that the last item is in final reservoir = The probability that one of the first k indexes is picked for last item =
    //        = k/n (the probability of picking one of the k items from a list of size n)
    //     2) let us now consider the second last item. The probability that the second last item is in final reservoir[] = [Probability that one of the first k indexes is picked in iteration for stream[n-2]] X [Probability that the index picked in iteration for stream[n-1] is not same as index picked for stream[n-2] ]
    //        = [k/(n-1)]*[(n-1)/n] = k/n.
    // Case 2: For first k stream items, i.e., for stream[i] where 0 <= i < k
    //     1) The probability that an item from stream[0..k-1] is in final array = Probability that the item is not picked when items stream[k], stream[k+1], …. stream[n-1] are considered:
    //        = [k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] = k/n


    // tag: math
    // time: O(n)
    // space: O(k)

    // A function to randomly select k items from stream[0..n-1].
    static void selectKItems(int stream[], int n, int k) {
        int i;   // index for elements in stream[]

        // reservoir[] is the output array. Initialize it with
        // first k elements from stream[]
        int reservoir[] = new int[k];
        for (i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        Random r = new Random();

        // Iterate from the (k+1)th element to nth element
        for (; i < n; i++) {
            // Pick a random index from 0 to i.
            int j = r.nextInt(i + 1);

            // If the randomly  picked index is smaller than k,
            // then replace the element present at the index
            // with new element from stream
            if (j < k) {
                reservoir[j] = stream[i];
            }
        }

        System.out.println("Following are k randomly selected items");
        System.out.println(Arrays.toString(reservoir));
    }

    //Driver Program to test above method
    public static void main(String[] args) {
        int stream[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int n = stream.length;
        int k = 5;
        selectKItems(stream, n, k);
    }
}
