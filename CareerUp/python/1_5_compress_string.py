"""
1.5 Implement a method to perform basic string compression using the counts
 of repeated characters. For example, the string aabcccccaaa would become
 a2b1c5a3. If the "compressed" string would not become smaller than the
 original string, your method should return the original string.
"""


def compress_string(string):
    # Time complexity: O(n) - string is iterated once
    # Space complexity: O(n) - created a new string of the same size
    if not string:
        return
    res = ""
    lastchar = string[0]
    num = 1
    for i in range(1, len(string)):
        if string[i] == lastchar:
            num += 1
            continue
        res += lastchar + str(num)
        lastchar = string[i]
        num = 1
    res += lastchar + str(num)
    return res if len(res) < len(string) else string


def main():
    s1 = "aabcccccaaa"
    s2 = "abc"
    print compress_string(s1) == "a2b1c5a3"
    print compress_string(s2) == "abc"


if __name__ == "__main__":
    main()
