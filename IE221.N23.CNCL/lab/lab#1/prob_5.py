n = int(input())

def is_lucky(num):
    if num == 0:
        return False
    while num > 0:
        digit = num % 10
        if digit != 4 and digit != 7:
            return False
        num //= 10
    return True
num_4 = str(n).count("4")
num_7 = str(n).count("7")
print("YES" if is_lucky(num_4+num_7) else "NO")
