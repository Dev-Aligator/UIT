index = int(input())
num_str = ""
i = 1
while index > len(num_str):
    num_str+= str(i)
    i+=1
print(num_str[index-1])
