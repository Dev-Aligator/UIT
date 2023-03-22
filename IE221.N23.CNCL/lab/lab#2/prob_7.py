n = int(input())
a = list(map(int, input().split()))

a.sort()
min_stability = a[-1] - a[0]
if len(a)==2:
    print(0)
    exit(0)
else:
    if a[-2] - a[0] > a[-1] - a[1]:
        min_stability = a[-1] - a[1]
    else:
        min_stability = a[-2] - a[0]

print(min_stability)
