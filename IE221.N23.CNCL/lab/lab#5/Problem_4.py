class SoNguyen:
    def __init__(self, n):
        self.n = n
        
    def xuat(self):
        print(self.n)
        
    def nhap(self):
        self.n = int(input("Nhap so nguyen: "))
        
    def cong(self, b):
        return SoNguyen(self.n + b.n)
    
    def tru(self, b):
        return SoNguyen(self.n - b.n)
    
    def nhan(self, b):
        return SoNguyen(self.n * b.n)
    
    def chia(self, b):
        return SoNguyen(self.n / b.n)
        
a = SoNguyen(0)
b = SoNguyen(0)

a.nhap()
b.nhap()

print("a + b = ", end="")
a.cong(b).xuat()

print("a - b = ", end="")
a.tru(b).xuat()

print("a * b = ", end="")
a.nhan(b).xuat()

try:
    print("a / b = ", end="")
    a.chia(b).xuat()
except:
    print("Cant divided by 0")

