n, m, h, w = map(int, input().split())
def folds(n,m,h,w):
    count = 0
    while n > h:
        h*=2
        count+=1
    while m > w:
        w*=2
        count+=1
    return count

print(min(folds(n,m,h,w),folds(m,n,h,w)))

        
