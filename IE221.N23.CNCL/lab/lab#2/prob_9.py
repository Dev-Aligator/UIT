n, m = map(int, input().split())
l = len(str(n))
count = m // 10**l
if m % 10**l >= n:
  count += 1
print(count)
