q = int(input())

for i in range(q):
    n = int(input())
    if n < 4:
        print(4-n)
    else:
        if n % 2 == 0:
            print(0)
        else:
            print(1)
