def giai_phuong_trinh_bac_hai(a, b, c):
    delta = b**2 - 4*a*c
    if delta < 0:
        return "PTVN"
    elif delta == 0:
        x = -b / (2*a)
        return "PT co nghiem kep: x1 = x2 = {}".format(int(x) if x.is_integer() else x )
    else:
        x1 = ((-b + delta**0.5) / (2*a))
        x2 = ((-b - delta**0.5 )/ (2*a))
        if x1.is_integer():
            x1 = int(x1)
        if x2.is_integer():
            x2 = int(x2)
        return "PT co hai nghiem phan biet:\nx1 = {}\nx2 = {}".format(x1, x2)

# Nhập các hệ số của phương trình
a = float(input())
b = float(input())
c = float(input())

# Giải phương trình bậc hai
print(giai_phuong_trinh_bac_hai(a, b, c))
