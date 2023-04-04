sentence = input()

num_letters = 0
num_digits = 0

for char in sentence:
    if char.isalpha():  # kiểm tra xem ký tự có phải là chữ cái không
        num_letters += 1
    elif char.isdigit():  # kiểm tra xem ký tự có phải là chữ số không
        num_digits += 1

print(num_letters)
print(num_digits)
