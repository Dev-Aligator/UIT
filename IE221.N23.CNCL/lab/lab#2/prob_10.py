n = int(input())
balance = 0

for i in range(n):
    line = input().split()
    if line[0] == 'D':
        balance += int(line[1])
    elif line[0] == 'W':
        balance -= int(line[1])

print(balance)
