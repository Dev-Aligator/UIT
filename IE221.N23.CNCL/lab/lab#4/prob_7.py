n = int(input())
cards = input()

count_8 = cards.count('8')

max_phones = min(count_8, n // 11)

print(max_phones)
