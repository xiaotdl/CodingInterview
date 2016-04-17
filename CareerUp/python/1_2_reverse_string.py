"""
1.2 Implement a function void reverse(char* str) in C or C++ which reverses a
 null terminated string.
"""


def reverse_string(string):
    # In python, str is immutable
    # Time complexity: O(n) - string is iterated once
    # Space complexity: O(n) - created a new string of the same size
    if not string:
        return
    res = ""
    for i in range(1, len(string) + 1):
        res += string[len(string) - i]
    return res


def main():
    s1 = "abcdefg"
    print reverse_string(s1) is s1[::-1]


if __name__ == "__main__":
    main()
