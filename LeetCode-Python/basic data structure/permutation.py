# import itertools
# print list(itertools.permutations([1,2,3], 2))
# print list(itertools.combinations([1,2,3], 2))
# print list(itertools.product([0,1], repeat=3))

def swap(s, a, b):
    if a == b: return s    
    if a < b:
        tmp = s[:a]+s[b]+s[a+1:b]+s[a]+s[b+1:]
    if a > b:
        tmp = s[:b]+s[a]+s[b+1:a]+s[b]+s[a+1:]
    return tmp


def permutation_recursive(s, start, res):
    if start == len(s) - 1:
        res.append(s)
        return

    for i in range(start, len(s)):
        s = swap(s, i, start)
        permutation_recursive(s, start + 1, res)
        # no need to swap back for python
        # s = swap(s, i, start)


def permutation_iterative(s):
    res = []
    if len(s) == 1:
        res = [s]
    else:
        for i, c in enumerate(s):
            for perm in permutation_iterative(s[:i] + s[i+1:]):
                res += [c + perm]

    return res

s = "123"

res = []
permutation_recursive(s, 0, res)
print res, "<recursive>"

print permutation_iterative(s), "<iterative>"
