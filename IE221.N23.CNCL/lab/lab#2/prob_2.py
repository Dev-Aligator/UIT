n, k = map(int, input().split())
s = input().strip()
count = [0]*k
for i in range(n):
    count[ord(s[i]) - ord('A')] +=1
print(min(count)*k)
