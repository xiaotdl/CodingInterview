"""
1.4 Write a method to replace all spaces in a string with '%20'. You may assume
 that the string has sufficient space at the end of the string to hold the
 additional characters, and that you are given the "true" length of the string.
 (Note: if implementing in Java, please use a character array so that you can
 perform this operation in place.)

EXAMPLE
Input: "Mr John Smith        "
Output: "Mr%20Dohn%20Smith"
"""


def replace_space(string, length):
    # str in Python is immutable
    # Iterate through a string reversely so that real string starts with the
    # first not space char
    # Time complexity: O(n) - string is iterated once
    # Space complexity: O(n) - created a new string of the same size
    res = ""
    replace = False
    for char in string[::-1]:
        if char == ' ':
            if not replace:
                continue
            res += "20%"[::-1]
        else:
            replace = True
            res += char
    return res[::-1]


def main():
    s1 = "Mr John Smith        "
    print replace_space(s1, len(s1)) == s1.rstrip().replace(' ', "20%")


if __name__ == "__main__":
    main()
