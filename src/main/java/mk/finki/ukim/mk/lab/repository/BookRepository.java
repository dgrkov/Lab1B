package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    public static List<Book> bookList = new ArrayList<>();

    @PostConstruct
    public void init(){
        bookList.add(new Book("ISBN1", "Book Title 1", "Genre 1", 2022, new ArrayList<>()));
        bookList.add(new Book("ISBN2", "Book Title 2", "Genre 2", 1990, new ArrayList<>()));
        bookList.add(new Book("ISBN3", "Book Title 3", "Genre 3", 1950, new ArrayList<>()));
        bookList.add(new Book("ISBN4", "Book Title 4", "Genre 4", 1987, new ArrayList<>()));
        bookList.add(new Book("ISBN5", "Book Title 5", "Genre 5", 2004, new ArrayList<>()));
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Book findByIsbn(String isbn) {
        return bookList.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);
        return author;
    }
}
