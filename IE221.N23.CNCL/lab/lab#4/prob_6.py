def is_palindrome(s):
    n = len(s)
    for i in range(n//2):
        if s[i] != s[n-i-1]:
            return False
    return True

s = input()
n = len(s)
count = 0
for i in range(n):
    for j in range(i+1, n+1):
        if is_palindrome(s[i:j]):
            count += 1
print(count)
