"""
1.1 Implement an algorithm to determine if a string has all unique characters.
 What if you cannot use additional data structures.
tips:
    encodings - anscii, latin1, unicode
    corner case - empty string's return value
"""


def unique_chars(string):
    # Assume ascii case
    # Time complexity: O(n) - string is iterated once
    # Space complexity: O(1) - fixed space, 128bit
    if not string or len(string) > 128:
        return False
    bools = [False] * 128
    for char in string:
        if not bools[ord(char)]:
            bools[ord(char)] = True
        else:
            return False
    return True


def main():
    s1 = "abcdefg"
    s2 = "aabc"
    print unique_chars(s1) is True
    print unique_chars(s2) is False


if __name__ == "__main__":
    main()
