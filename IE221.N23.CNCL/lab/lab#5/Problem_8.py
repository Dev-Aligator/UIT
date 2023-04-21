class String:
    def __init__(self, s=""):
        self.str = s

    def input_string(self):
        self.str = input("Nhập chuỗi: ")

    def output_string(self):
        print("Chuỗi: ", self.str)

    def length(self):
        return len(self.str)

    def concat(self, s):
        return self.str + s

    def reverse(self):
        return self.str[::-1]

s = String()
s.input_string()
s.output_string()
print("Length:", s.length())
print("Concat result: " + s.concat("Concat part"))
print("Reverse: " + s.reverse())
