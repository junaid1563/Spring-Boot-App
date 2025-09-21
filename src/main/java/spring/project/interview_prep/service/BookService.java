package spring.project.interview_prep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.project.interview_prep.Model.Book;
import spring.project.interview_prep.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        System.out.println(book.getBookName());
        System.out.println(book.getAuthorName());
        System.out.println(book.getCountry());
        System.out.println(book.getId());
        System.out.println(book.getCost());
        try {
            if (checkForDuplicate(book)) {
                bookRepository.save(book);
            } else {
                throw new RuntimeException("Duplicate Book Entry");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkForDuplicate(Book book) {
        int count = bookRepository.findDuplicate(book.getBookName().trim(),
                book.getAuthorName().trim(), book.getCountry().trim(), book.getCost());
        return count == 0;
    }

    public Book getBookByAuthor(String authorName) {
        return bookRepository.findBookByAuthor(authorName);
    }

    public Book getBookByCountry(String country) {
        return bookRepository.findBookByCountry(country);
    }

    public void updateBook(int id, Book newBook) {
        Book book = new Book();
        book.setBookName(newBook.getBookName());
        book.setAuthorName(newBook.getAuthorName());
        book.setCountry(newBook.getCountry());
        book.setCost(newBook.getCost());


        bookRepository.updateBook(book.getBookName(), book.getAuthorName(), book.getCountry(), book.getCost(), id);
    }
}
