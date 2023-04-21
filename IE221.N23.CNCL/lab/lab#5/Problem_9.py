class Rectangle:
    def __init__(self, length, width):
        self.length = length
        self.width = width

    def calcPerimeter(self):
        return 2 * (self.length + self.width)

    def calcArea(self):
        return self.length * self.width

    def display(self):
        print("Chiều dài:", self.length)
        print("Chiều rộng:", self.width)
        print("Chu vi:", self.calcPerimeter())
        print("Diện tích:", self.calcArea())


class Parallelpipe(Rectangle):
    def __init__(self, length, width, height):
        super().__init__(length, width)
        self.height = height

    def volume(self):
        return self.calcArea() * self.height


rect = Rectangle(5, 3)
rect.display()

para = Parallelpipe(5, 3, 2)
print("Thể tích hình Parallelpipe:", para.volume())

