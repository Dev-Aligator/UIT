
a = float(input())
b = float(input())
c = float(input())

ls = sorted([a,b,c])

[print(int(x) if x.is_integer() else x, end= ' ') for x in ls]
