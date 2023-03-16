n = int(input())
if n < 1 or n > 30:
    print("So", n, "khong nam trong khoang [1,30].")
else:
    if n == 1:
        print(1)
    else:
        a,b = 0,1
        for i in range(2, n + 1):
            c = a+b 
            a,b = b,c 
        print(b)
