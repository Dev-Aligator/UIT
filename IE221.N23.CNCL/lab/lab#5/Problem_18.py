class NhanVien:
    def __init__(self, ho_ten, ngay_sinh, luong):
        self.ho_ten = ho_ten
        self.ngay_sinh = ngay_sinh
        self.luong = luong

class NhanVienVanPhong(NhanVien):
    def __init__(self, ho_ten, ngay_sinh, so_ngay_lam_viec):
        super().__init__(ho_ten, ngay_sinh, 0)
        self.so_ngay_lam_viec = so_ngay_lam_viec

    def tinh_luong(self):
        self.luong = self.so_ngay_lam_viec * 100000

class NhanVienSanXuat(NhanVien):
    def __init__(self, ho_ten, ngay_sinh, so_san_pham):
        super().__init__(ho_ten, ngay_sinh, 0)
        self.so_san_pham = so_san_pham

    def tinh_luong(self):
        self.luong = self.so_san_pham * 5000 + 1000000

nhan_viens = []
nhan_viens.append(NhanVienVanPhong("Nguyen Hoang Tan", "21/12/2003", 20))
nhan_viens.append(NhanVienSanXuat("Pham Tram Anh", "20/09/2003", 30))

for nv in nhan_viens:
    nv.tinh_luong()
    print(f"Ten: {nv.ho_ten}, Luong: {nv.luong}")

