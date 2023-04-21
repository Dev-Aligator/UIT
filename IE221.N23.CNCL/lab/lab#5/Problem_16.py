class SinhVien:
    def __init__(self, ma_sv, ho_ten, diem_gt, diem_gd, diem_lap_trinh):
        self.ma_sv = ma_sv
        self.ho_ten = ho_ten
        self.diem_gt = diem_gt
        self.diem_gd = diem_gd
        self.diem_lap_trinh = diem_lap_trinh
        self.diem_tb = round((diem_gt + diem_gd + diem_lap_trinh) / 3, 2)

    def is_xuat_sac(self):
        return self.diem_tb >= 8.0 and self.diem_lap_trinh >= 9.0

    def __str__(self):
        return f"{self.ma_sv}\t{self.ho_ten}\t{self.diem_gt}\t{self.diem_gd}\t{self.diem_lap_trinh}\t{self.diem_tb}"


def input_ds_sinh_vien():
    n = int(input("Nhập số lượng sinh viên: "))
    ds_sinh_vien = []
    for i in range(n):
        print(f"Nhập thông tin sinh viên thứ {i+1}:")
        ma_sv = input("Mã số sinh viên: ")
        ho_ten = input("Họ tên sinh viên: ")
        diem_gt = float(input("Điểm môn Giải tích: "))
        diem_gd = float(input("Điểm môn Giới thiệu ngành: "))
        diem_lap_trinh = float(input("Điểm môn Nhập môn lập trình: "))
        sinh_vien = SinhVien(ma_sv, ho_ten, diem_gt, diem_gd, diem_lap_trinh)
        ds_sinh_vien.append(sinh_vien)
    return ds_sinh_vien


def liet_ke_sinh_vien_hoc_bong(ds_sinh_vien):
    print("Danh sách sinh viên được nhận học bổng:")
    for sinh_vien in ds_sinh_vien:
        if sinh_vien.is_xuat_sac():
            print(sinh_vien)


def sap_xep_danh_sach_sinh_vien(ds_sinh_vien):
    return sorted(ds_sinh_vien, key=lambda x: x.diem_tb, reverse=True)

def sinh_vien_gioi_nhat(ds_sinh_vien):
    print("Sinh vien co diem trung binh cao nhat: ")
    for sinh_vien in ds_sinh_vien:
        if sinh_vien.diem_tb != ds_sinh_vien[0].diem_tb:
            break;
        print(sinh_vien)
def xuat_danh_sach_top_10_sinh_vien(ds_sinh_vien):
    print("Danh sách top 10 sinh viên có điểm trung bình cao nhất:")
    for sinh_vien in ds_sinh_vien[:10]:
        print(sinh_vien)


# Test the functions
ds_sinh_vien = input_ds_sinh_vien()
liet_ke_sinh_vien_hoc_bong(ds_sinh_vien)
ds_sinh_vien = sap_xep_danh_sach_sinh_vien(ds_sinh_vien)
sinh_vien_gioi_nhat(ds_sinh_vien)
xuat_danh_sach_top_10_sinh_vien(ds_sinh_vien)

