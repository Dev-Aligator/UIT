k, t = map(int, input().split())
remainder = t % (2*k)
if remainder <= k:
    print(remainder)
else:
    print(2*k - remainder)
