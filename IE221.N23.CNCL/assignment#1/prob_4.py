
x = str(abs(int(input())))
i = 0
while i < len(x)/2:
    if x[i] != x[-i-1]:
        print("false")
        exit()
    i+=1
print("true")
