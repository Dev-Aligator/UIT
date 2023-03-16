nguyenam = ['a', 'o', 'y', 'e', 'u', 'i']
string = input().lower()


result = ""

for char in string:
    if char in nguyenam:
        continue
    else:
        result += "." + char

print(result)
