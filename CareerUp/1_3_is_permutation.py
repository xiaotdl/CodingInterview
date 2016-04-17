"""
1.3 Given two strings, write a method to decide if one is a permutation of
 the other.
tips:
    assumption - case sensitive, whitespace matters
"""


def is_permutation(str1, str2):
    # Sort string and compare
    # Time complexity: O(nlogn) - sorting
    # Space complexity: O(1) - in place
    if not str1 or not str2 or len(str1) != len(str2):
        return False
    return ''.join(sorted(str1)) == ''.join(sorted(str2))


def main():
    s1 = "abc"
    s2 = "cba"
    s3 = "aaa"
    print is_permutation(s1, s2) is True
    print is_permutation(s1, s3) is False


if __name__ == "__main__":
    main()
