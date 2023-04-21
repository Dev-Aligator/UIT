class Candidate:
    def __init__(self, code, name, dob, math, literature, english):
        self.code = code
        self.name = name
        self.dob = dob
        self.math = math
        self.literature = literature
        self.english = english
    
    def input(self):
        self.code = input("Nhap ma so: ")
        self.name = input("Nhap ho ten: ")
        self.dob = input("Nhap ngay sinh: ")
        self.math = float(input("Nhap diem Toan: "))
        self.literature = float(input("Nhap diem Van: "))
        self.english = float(input("Nhap diem Anh: "))
    
    def output(self):
        print("Thong tin thi sinh:")
        print(f"Ma so: {self.code}")
        print(f"Ho ten: {self.name}")
        print(f"Ngay sinh: {self.dob}")
        print(f"Diem Toan: {self.math}")
        print(f"Diem Van: {self.literature}")
        print(f"Diem Anh: {self.english}")
    
    def get_total_score(self):
        return self.math + self.literature + self.english
    
def TestCandidate(candidates):
    for candidate in candidates:
        if candidate.get_total_score() > 15:
            candidate.output()

n = int(input("Nhap so luong thi sinh: "))
while n < 3:
    n = int(input("Nhap so luong thi sinh >= 3: "))
candidates = []
for i in range(n):
    candidate = Candidate("", "", "", 0, 0, 0)
    candidate.input()
    candidates.append(candidate)

TestCandidate(candidates)

