class Book:
    def __init__(self, title, author, price):
        self.title = title
        self.author = author
        self.price = price

    def display_info(self):
        print("Title:", self.title)
        print("Author:", self.author)
        print("Price:", self.price)

    def calculate_total_price(self, n):
        return n * self.price

name = input("Enter Book name: ")
author = input("Enter author name: ")
price = float(input("Enter book's prce: "))

book = Book(name, author, price)
book.display_info()

n = int(input("Enter the number of books to buy: "))
total_price = book.calculate_total_price(n)
print("Total price for {} books: {:.2f} VND".format(n, total_price))

