[序列型DP]dp[i]表示前i个数的和：dp[A.length + 1], dp[i] => {} || A[0..i-1]
[坐标型DP]dp[i]表示第i个数：dp[A.length], dp[i] => A[i]


序列型DP
1. 状态 State
    f[i] 表示前i个房子中，累积状态
2. 方程 Function
    f[i] = max(f[i-1], f[i-2] + A[i-1]);
3. 初始化 Initialization
    f[0] = 0; f[1] = A[0];
4. 答案 Answer
    f[n]


[记忆化搜索]
什么时候用记忆化搜索？
1. 状态转移特别麻烦，不是顺序性。
2. 初始化状态不是很容易找到。

// 循环求所有状态
for i = 1 -> m
    for j = 1 -> n
        dp[i][j] = search(i, j) // 求以i, j为结尾的结果

int search(int x, int y, int[][] A) {
    if (isVisited[x][y]) {
        return dp[x][y]; // 遍历过直接返回
    }

    dp[x][y] = 0; // 1 for count
    for (int k = 0; k < 4; i++) {
        int nextX = x + dx[k];
        int nextY = y + dy[k];
        if (inBound(m, n, nextX, nextY) && compare(A[x][y], A[nextX][nextY]) {
            dp[x][y] = Math.max(dp[x][y], search(nextX, nextY, A));
        }
    }
    return dp[x][y];
}
