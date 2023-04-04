n, m = map(int, input().split())

cut_length = 0
num = min(n // 2 , m // 2) - 1
cut_length = num*(2*(m+n) - 8) + num*(num-1)*(-4)
n -= num*2
m -= num*2
while True:
    if n - 1 > 0:
        cut_length+= n - 1
    else:
        break
    if m -2 > 0:
        cut_length += m - 2
    else:
        break
    if n - 2> 0:
        cut_length += n -2 
    else:
        break
    if m - 3 > 0:
        cut_length += m - 3
    else:
        break
    n-=2
    m-=2
    


print(cut_length)
