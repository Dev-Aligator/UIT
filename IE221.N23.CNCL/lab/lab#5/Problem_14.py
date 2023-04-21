class Polynomial:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def value(self, x):
        return self.a * x + self.b

    def root(self):
        return -self.b / self.a

    def add(self, other):
        return Polynomial(self.a + other.a, self.b + other.b)


p1 = Polynomial(2, 3)

print(p1.value(4))  

print(p1.root())  

p2 = Polynomial(5, 7)

p3 = p1.add(p2)
print(p3.value(2))  

