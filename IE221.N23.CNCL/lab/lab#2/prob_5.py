n = int(input())
citations = list(map(int, input().split()))

citations.sort(reverse=True)
h_index = 0

for i in range(n):
    if citations[i] >= i+1:
        h_index = i+1
    else:
        break

print(h_index)
