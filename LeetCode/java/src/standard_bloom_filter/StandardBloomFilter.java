package standard_bloom_filter;

import java.util.*;

/**
 * Created by Xiaotian on 4/2/18.
 */


// 标准布隆过滤器的作用相当于一个HashSet，即提供了这样一个数据结构，他支持如下操作：
//     O(1)在集合中加入一个元素
//     O(1)判断一个元素是否在集合中（允许 False Positive）
// 实现:
// 插入一个元素：通过 k 个哈希函数，计算出元素的 k 个哈希值，对 boolean 数组的长度取模之后，标记对应的 k 个位置上的值为 true。
// 查询一个元素：通过同样的 k 哈希函数，在 boolean 数组中取出对应的 k 个位置上的值。如果都是 true，则表示该元素可能存在，如果有一个 false，则表示一定不存在。


// Implement a standard bloom filter. Support the following method:
//   1. StandardBloomFilter(k),The constructor and you need to create k hash functions.
//   2. add(string). add a string into bloom filter.
//   3. contains(string). Check a string whether exists in bloom filter.
// e.g.
//   StandardBloomFilter(3)
//   add("lint")
//   add("code")
//   contains("lint") // return true
//   contains("world") // return false

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


class StandardBloomFilter {

    public int CAPACITY = 100000;

    public int k;
    public BitSet bitset;
    public List<HashFunction> hashFunctions;
    public StandardBloomFilter(int k) {
        this.k = k;
        bitset = new BitSet(CAPACITY + k);
        hashFunctions = new ArrayList<HashFunction>();
        for (int i = 0; i < k; i++) {
            hashFunctions.add(new HashFunction(CAPACITY + i, 2 * i + 3));
        }
    }

    public void add(String word) {
        for (int i = 0; i < k; i++) {
            HashFunction f = hashFunctions.get(i);
            int pos = f.hash(word);
            bitset.set(pos);
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < k; i++) {
            HashFunction f = hashFunctions.get(i);
            int pos = f.hash(word);
            if (!bitset.get(pos)) {
                return false;
            };
        }
        return true;
    }
}
