class Employee:
    def __init__(self, name, dob):
        self.name = name
        self.dob = dob
        
class OfficeStaff(Employee):
    def __init__(self, name, dob, basic_salary, working_days, allowance):
        super().__init__(name, dob)
        self.basic_salary = basic_salary
        self.working_days = working_days
        self.allowance = allowance
        
    def calculate_salary(self):
        return self.basic_salary + (self.working_days * 100000) + self.allowance
        
class ProductionStaff(Employee):
    def __init__(self, name, dob, basic_salary, products):
        super().__init__(name, dob)
        self.basic_salary = basic_salary
        self.products = products
        
    def calculate_salary(self):
        return self.basic_salary + (self.products * 2000)
        
class Manager(Employee):
    def __init__(self, name, dob, basic_salary, position_factor, bonus):
        super().__init__(name, dob)
        self.basic_salary = basic_salary
        self.position_factor = position_factor
        self.bonus = bonus
        
    def calculate_salary(self):
        return self.basic_salary * self.position_factor + self.bonus
        
class Company:
    def __init__(self, employees):
        self.employees = employees
        
    def calculate_total_salary(self):
        total_salary = 0
        for employee in self.employees:
            total_salary += employee.calculate_salary()
        return total_salary
        
    def search_employee(self, name):
        for employee in self.employees:
            if employee.name == name:
                return employee
        return None
        
    def print_employee_info(self):
        for employee in self.employees:
            print(f"Name: {employee.name}, DOB: {employee.dob}")
            if isinstance(employee, OfficeStaff):
                print(f"Salary: {employee.calculate_salary()}")
            elif isinstance(employee, ProductionStaff):
                print(f"Salary: {employee.calculate_salary()}")
            elif isinstance(employee, Manager):
                print(f"Salary: {employee.calculate_salary()}")


staff1 = OfficeStaff("Tan", "2003-12-21", 5000000, 20, 2000000)
staff2 = ProductionStaff("Tram Anh", "2003-09-20", 4000000, 100)
manager1 = Manager("Shenka", "2003-10-02", 10000000, 2.5, 3000000)

company = Company([staff1, staff2, manager1])
company.print_employee_info()

print(f"Total salary: {company.calculate_total_salary()}")

search_name = "Tram Anh"
employee = company.search_employee(search_name)
if employee:
    print(f"{search_name} is found")
else:
    print(f"{search_name} is not found")

