n = int(input())
x,y = map(int,input().split())

white_moves = max(x,y)-1
black_moves = abs(min(x,y) -n)
print("Black" if black_moves < white_moves else "White")
