m,n = map(int,input().split())
mod = 1000000007

result = 1

while n > 0:
    if n % 2 == 1:
        result = (result * m) % mod
    m = (m * m) % mod
    n = n // 2

print(result)
