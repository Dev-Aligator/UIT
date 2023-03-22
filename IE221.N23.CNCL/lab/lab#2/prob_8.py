matrix = [list(map(int, input().split())) for _ in range(5)]

for i in range(5):
    for j in range(5):
        if matrix[i][j] == 1:
            row = i
            col = j
            break


print((abs(row-2) + abs(col-2)))
