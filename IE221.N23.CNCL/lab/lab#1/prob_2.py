a,b,c = map(float,input().split())
x = a ** 5 - 2*(b if b > 0 else -b)**0.5 + a * b * c

print(f"{x:.2f}")
