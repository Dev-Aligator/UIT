x = input()
for i in range(4):
    for j in range(6):
        if i == 0 or i ==3 or j == 0 or j == 5:
            print(x, end=' ')
        else:
            print(" ", end=' ')
    print()
for i in range(6):
    if i == 0:
        print(" "*(5-i) + x)
    elif i == 5:
        print((x + " ") *6)
    else:
        print(" "*(5-i) + x + " "*(2*i-1) + x)

