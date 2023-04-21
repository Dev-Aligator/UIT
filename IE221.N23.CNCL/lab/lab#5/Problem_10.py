class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age
    
    def Display(self):
        print("Name:", self.name)
        print("Age:", self.age)

class Student(Person):
    def __init__(self, name, age, student_id):
        super().__init__(name, age)
        self.student_id = student_id
    
    def displayStudent(self):
        self.Display()
        print("Student ID:", self.student_id)

student = Student("Hoang Tan", 19, "21521413")
student.displayStudent()

