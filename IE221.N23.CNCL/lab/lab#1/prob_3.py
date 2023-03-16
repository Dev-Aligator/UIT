n = int(input())
sum_divisors = 0
i = 1

while i <= n/i:
    if n % i == 0:
        if i == n/i:
            sum_divisors += i
        else:
            sum_divisors += i + n//i
    i += 1

print(sum_divisors-n)
