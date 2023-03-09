from math import prod
x = int(input())
print(prod(list(range(1 if x % 2 else 2,x+2,2))))
