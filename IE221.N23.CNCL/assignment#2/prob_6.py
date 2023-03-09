age = int(input())
gender = input().lower()
if gender != "m" and gender != "f":
    print("I do not know why")
    exit(0)
check_gender = lambda g : 1 if g == 'm' else 2
if age < 21:
    print(check_gender(gender) + 2)
else:
    print(check_gender(gender))
