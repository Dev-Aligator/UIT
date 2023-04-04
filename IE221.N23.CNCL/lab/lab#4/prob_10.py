n = int(input())
a = sorted(list(map(int, input().split())))

first = a[0] - 0
last = a[-1]

print(last - first + 1 - n)
