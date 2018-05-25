package counting_blooming_filter;

import java.util.*;

/**
 * Created by Xiaotian on 4/2/18.
 */


// 基于标准的 BloomFilter 稍加改动，把存储所用的 boolean 数组改为 int 数组，
// 就成为了可以计数的 BloomFilter -> Counting Bloom Filter（简写为CBF）。
// 这种数据结构类似 Java 中的 HashMap，但只能用作计数。提供如下的几种操作：
//     O(1)时间内，在集合中加入一个元素
//     O(1)时间内，统计某个元素在该集合中出现的次数 - 但是可能会比实际出现次数要大一些


class HashFunction {
    public int capacity, base;

    public HashFunction(int capacity, int base) {
        this.capacity = capacity;
        this.base = base;
    }

    public int hash(String s) {
        int hashCode = 0;
        for (char c : s.toCharArray()) {
            hashCode = (hashCode * base + c) % capacity;
        }
        return hashCode;
    }
}

class CountingBloomingFilter {

    public int CAPACITY = 100000;

    public int k;
    public int[] bitSet;
    public List<HashFunction> hashFunctions;
    public CountingBloomingFilter(int k) {
        this.k = k;
        bitSet = new int[CAPACITY + k];
        hashFunctions = new ArrayList<HashFunction>();
        for (int i = 0; i < k; i++) {
            hashFunctions.add(new HashFunction(CAPACITY + i, 2 * i + 3));
        }
    }

    public void add(String word) {
        for (int i = 0; i < k; i++) {
            HashFunction f = hashFunctions.get(i);
            int pos = f.hash(word);
            bitSet[pos]++;
        }
    }
    public void remove(String word) {
        for (int i = 0; i < k; i++) {
            HashFunction f = hashFunctions.get(i);
            int pos = f.hash(word);
            bitSet[pos]--;
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < k; i++) {
            HashFunction f = hashFunctions.get(i);
            int pos = f.hash(word);
            if (bitSet[pos] <= 0) {
                return false;
            };
        }
        return true;
    }
}
