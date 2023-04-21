class ComplexNumber:
    def __init__(self, real, imag):
        self.real = real
        self.imag = imag

    def display(self):
        if self.imag >= 0:
            print("{} + {}i".format(self.real, self.imag))
        else:
            print("{} - {}i".format(self.real, abs(self.imag)))

    def add(self, other):
        return ComplexNumber(self.real + other.real, self.imag + other.imag)

    def subtract(self, other):
        return ComplexNumber(self.real - other.real, self.imag - other.imag)

    def multiply(self, other):
        real_part = self.real * other.real - self.imag * other.imag
        imag_part = self.real * other.imag + self.imag * other.real
        return ComplexNumber(real_part, imag_part)

    def divide(self, other):
        denominator = other.real ** 2 + other.imag ** 2
        real_part = (self.real * other.real + self.imag * other.imag) / denominator
        imag_part = (self.imag * other.real - self.real * other.imag) / denominator
        return ComplexNumber(real_part, imag_part)


print("Enter the first complex number:")
real1 = float(input("Real part: "))
imag1 = float(input("Imaginary part: "))
c1 = ComplexNumber(real1, imag1)

print("Enter the second complex number:")
real2 = float(input("Real part: "))
imag2 = float(input("Imaginary part: "))
c2 = ComplexNumber(real2, imag2)

print("First complex number: ", end="")
c1.display()
print("Second complex number: ", end="")
c2.display()

print("Sum of the two complex numbers: ", end="")
c1.add(c2).display()

print("Difference of the two complex numbers: ", end="")
c1.subtract(c2).display()

print("Product of the two complex numbers: ", end="")
c1.multiply(c2).display()

try:
    print("Division of the two complex numbers: ", end="")
    c1.divide(c2).display()
except:
    print("Can't divide the two complex numbers")

