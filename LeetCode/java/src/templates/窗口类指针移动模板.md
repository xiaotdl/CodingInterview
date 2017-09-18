2层循环滑动左右指针
for loop 自动i++，只需关注while loop什么条件j++
int i, j;
for (i = 0, j = 0; i < n; i++) { // move left ptr
    while (j < n) {
        if (满足条件) {
            更新j相关状态
            j++; // move right ptr
        }
        else {
            break;
        }
    }
    更新min/max状态
    更新i相关状态
}


## Questions
[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)
[209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/description/)
[159. Longest Substring with At Most Two Distinct Characters]
[340. Longest Substring with At Most K Distinct Characters]
[19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/)???
