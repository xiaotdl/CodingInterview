//先介绍一下原码，反码和补码
//原码，就是其二进制表示（注意，有一位符号位）
//反码，正数的反码就是原码，负数的反码是符号位不变，其余位取反
//补码，正数的补码就是原码，负数的补码是反码+1
//在机器中都是采用补码形式存负数

判断int中第i位是否为1:
(x >> i & 1) == 1
消去最右侧的1:
x & (x - 1)
得到最右侧的1:
x - x & (x - 1)


## 技巧一：消去二进制中最右侧的那个1
x & (x - 1)

e.g.
  1100 x
& 1011 x-1
  ----
  1000

### 应用一：O(1)时间检测2的幂次
http://www.lintcode.com/zh-cn/problem/o1-check-power-of-2/

### 应用二：计算在一个32位的整数的二进制表式中有多少个1.
http://www.lintcode.com/zh-cn/problem/count-1-in-binary/
Note:
Integer.MIN_VALUE = -2147483648
    10000000 00000000 00000000 00000000
Integer.MAX_VALUE = 2147483647
    01111111 11111111 11111111 11111111
Integer.MAX_VALUE + 1 = Integer.MIN_VALUE

### 应用三：如果要将整数A转换为B，需要改变多少个bit位？
http://www.lintcode.com/zh-cn/problem/flip-bits/


## 技巧二：使用二进制进行子集枚举
思路就是使用一个正整数二进制表示的第i位是1还是0来代表集合的第i个数取或者不取。
所以从0到2^n-1总共2^n个整数，正好对应集合的2^n个子集。如下是就是 整数 <=> 二进制 <=> 对应集合 之间的转换关系。

### 应用：给定一个含不同整数的集合，返回其所有的子集
http://www.lintcode.com/en/problem/subsets/


## 技巧三：巧用异或运算
a ^ b ^ b = a // 对一个数异或两次等价于没有任何操作！

### 应用
http://www.lintcode.com/en/problem/single-number-i/
http://www.lintcode.com/en/problem/single-number-ii/
http://www.lintcode.com/en/problem/single-number-iii/

