a, b, c = map(int, input().split())
p = (a + b + c)/2
print("{:.2f}".format((p*(p-a)*(p-b)*(p-c))**0.5))
