int l = 0, r = max;             // 1. 找到可行解范围
while (l + 1 < r) {
    int m = l + (r - l) / 2;    // 2. 二分猜答案
    if (check(m)) {             // 3. 检验答案
        l = m;                  // 4. 调整搜索范围
    }
    else {
        r = m;                  // 4. 调整搜索范围
    }
}

退出[l, r]                       // 5. 从l，r中找答案