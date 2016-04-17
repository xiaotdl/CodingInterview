"""
Assume you have a method isSubstring which checks if one word is a substring
 of another. Given two strings, s1 and s2, write code to check if s2 is a
 rotation of s1 using only one call to isSubstring
 (e.g.,"waterbottle"is a rotation of"erbottlewat").
"""


def is_rotation(s1, s2):
    return (s1 in s2 + s2)


def main():
    s1 = "waterbottle"
    s2 = "erbottlewat"
    print is_rotation(s1, s2) is True


if __name__ == "__main__":
    main()
