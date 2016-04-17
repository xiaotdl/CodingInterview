class Klass():
    var = 0 # instance variable
    def __init__(self, l=[]):
        self.l = l
    def next(self):
        self.var += 1
        if self.var < 5:
            return self.var
        else:
            return None

i1 = Klass()
i1.l.append(123)
i1.l.append(123)
print i1.l

i2 = Klass()
i2.l.append(456)
i2.l.append(456)
print i2.l
