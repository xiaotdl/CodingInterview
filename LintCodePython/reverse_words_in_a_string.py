class Solution:
    # @param s : A string
    # @return : A string
    def reverseWords1(self, s):
        # [string], intuitive, Python makes life easier
        return ' '.join(reversed(s.split()))

    def reverseWords2(self, s):
        if not s:
            return ''

        return self._join(self._reversed(self._split(s)))

    def _split(self, s):
        words = []
        word = ''
        for char in s:
            if char != ' ':
                word += char
            elif word != '':
                words.append(word)
                word = ''
        if word != '':
            words.append(word)
        return words

    def _reversed(self, l):
        for i in range(len(l) / 2):
            l[i], l[len(l) - 1 - i] = l[len(l) - 1 - i], l[i]
        return l

    def _join(self, words):
        s = ''
        for i in range(len(words)):
            s += words[i]
            if i != len(words) - 1:
                s += ' '
        return s


if __name__ == '__main__':
    print Solution().reverseWords1("the sky is blue")
    print Solution().reverseWords2("the sky is blue")
# >>>
# blue is sky the
# blue is sky the
