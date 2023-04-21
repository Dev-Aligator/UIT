import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class LibraryFacade {
    private UserManager userManager;
    private BookManager bookManager;
    private LoanManager loanManager;
    private User currentUser;

    public LibraryFacade() {
        userManager = new UserManager();
        bookManager = new BookManager();
        loanManager = new LoanManager();
        currentUser = null;
    }

    public void registerUser(String username, String password, String fullName, String email) {
        userManager.registerUser(username, password, fullName, email);
    }

    public void loginUser(String username, String password) {
        currentUser = userManager.loginUser(username, password);
    }

    public void logoutUser() {
        currentUser = null;
    }

    public List<Book> searchBooks(String searchTerm) {
        return bookManager.searchBooks(searchTerm);
    }

    public boolean checkOutBook(Book book) {
        return loanManager.checkOutBook(currentUser, book);
    }

    public boolean returnBook(Book book) {
        return loanManager.returnBook(currentUser, book);
    }
    public void addBook(Book book){
        bookManager.addBook(book);
    }
}

class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password, String fullName, String email) {
        User user = new User(username, password, fullName, email);
        users.put(username, user);
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}

class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 3));
        books.add(new Book("1984", "George Orwell", 4));
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> searchBooks(String searchTerm) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(searchTerm) || book.getAuthor().contains(searchTerm)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
}

class LoanManager {
    private Map<User, List<Book>> loans;

    public LoanManager() {
        loans = new HashMap<>();
    }

    public boolean checkOutBook(User user, Book book) {
        if (!loans.containsKey(user)) {
            loans.put(user, new ArrayList<>());
        }
        List<Book> userLoans = loans.get(user);
        if (userLoans.contains(book)) {
            return false;
        } else {
            userLoans.add(book);
            return true;
        }
    }

    public boolean returnBook(User user, Book book) {
        if (!loans.containsKey(user)) {
            return false;
        }
        List<Book> userLoans = loans.get(user);
        if (!userLoans.contains(book)) {
            return false;
        } else {
            userLoans.remove(book);
            return true;
        }
    }
}

class User {
    private String username;
    private String password;
    private String fullName;
    private String email;

    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


class Book {
    private String title;
    private String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}


public class Problem_3{
    public static void main(String[] args){
        LibraryFacade library = new LibraryFacade();
        library.registerUser("Tan", "1234", "Hoang Tan", "21521413@gm.uit.edu.vn");

        library.loginUser("Tan", "1234");

        Book book1 = new Book("Design Patterns OOP", "Someone", 10);
        library.addBook(book1);

        List<Book> books = library.searchBooks("1984");
        System.out.println("Books found: ");
        for (Book book: books){
            System.out.println(book.getTitle() + " " + book.getAuthor());
        }

        boolean success = library.checkOutBook(books.get(0));
        System.out.println("Checkout success: " + success);

        success = library.returnBook(books.get(0));
        System.out.println("Return success: " + success);

        library.logoutUser();
    }
}
