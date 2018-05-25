package cosine_similarity;

import java.util.*;

/**
 * Created by Xiaotian on 4/4/18.
 */
public class Solution {
    // https://en.wikipedia.org/wiki/Cosine_similarity
    // Cosine similarity is a measure of similarity between
    // two non-zero vectors of an inner product space that
    // measures the cosine of the angle between them. The
    // cosine of 0° is 1, and it is less than 1 for any other
    // angle in the interval [0,2π).
    // formula:
    // A*B = |A|*|B|*cos(theta)
    // similarity = cos(theta) = A*B/|A|*|B|
    //                         = sum(Ai*Bi for i in 1..n) / (sqrt(sum(Ai^2 for i in 1..n)) * sqrt(sum(Bi^2 for i in 1..n)))
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        if (vectorA.length != vectorB.length) return 0.0;

        int len = vectorA.length;
        double dotProduct = 0.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;
        for (int i = 0; i < len; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            magnitudeA += Math.pow(vectorA[i], 2);
            magnitudeB += Math.pow(vectorB[i], 2);
        }
        magnitudeA = Math.sqrt(magnitudeA);
        magnitudeB = Math.sqrt(magnitudeB);

        return dotProduct / (magnitudeA * magnitudeB);
    }

    // https://blog.nishtahir.com/2015/09/19/fuzzy-string-matching-using-cosine-similarity/
    public static Map<String, Integer> getTermFrequencyMap(String[] terms) {
        Map<String, Integer> termFrequencyMap = new HashMap<>();
        for (String term : terms) {
            Integer n = termFrequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            termFrequencyMap.put(term, n);
        }
        return termFrequencyMap;
    }

    public static double cosineSimilarity(String text1, String text2) {
        //Get vectors
        Map<String, Integer> a = getTermFrequencyMap(text1.split("\\W+"));
        Map<String, Integer> b = getTermFrequencyMap(text2.split("\\W+"));

        //Get unique words from both sequences
        HashSet<String> intersection = new HashSet<>(a.keySet());
        intersection.retainAll(b.keySet());

        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (String item : intersection) {
            dotProduct += a.get(item) * b.get(item);
        }

        //Calculate magnitude a
        for (String k : a.keySet()) {
            magnitudeA += Math.pow(a.get(k), 2);
        }
        magnitudeA = Math.sqrt(magnitudeA);


        //Calculate magnitude b
        for (String k : b.keySet()) {
            magnitudeB += Math.pow(b.get(k), 2);
        }
        magnitudeB = Math.sqrt(magnitudeB);

        //return cosine similarity
        return dotProduct / (magnitudeA * magnitudeB);
    }

    public static void main(String[] args) {
        System.out.println(cosineSimilarity(new double[]{10, 1}, new double[]{2, 1}));
        System.out.println(cosineSimilarity(new double[]{1, 1}, new double[]{1, 1}));
        System.out.println(cosineSimilarity(new double[]{1, 0.5}, new double[]{0.5, 1}));
    }
}
