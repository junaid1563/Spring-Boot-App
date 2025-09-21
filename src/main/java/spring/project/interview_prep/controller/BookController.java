package spring.project.interview_prep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.project.interview_prep.Model.Book;
import spring.project.interview_prep.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> getAllBooks() {
        System.out.println("Inside getAll method");
        return bookService.getAllBooks();
    }

    @PostMapping("/add-book")
    public void addBook(@RequestBody Book book) throws Exception {
        System.out.println("Inside post method");
        try {
            System.out.println(book.getBookName());
            System.out.println(book.getAuthorName());
            System.out.println(book.getCountry());
            System.out.println(book.getId());
            System.out.println(book.getCost());
            bookService.addBook(book);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/book-by-author/{authorName}")
    public Book getBookByAuthor(@PathVariable("authorName") String authorName) {
        return bookService.getBookByAuthor(authorName);
    }

    @GetMapping("/book-by-country/{country}")
    public Book getBookByCountry(@PathVariable("country") String country) {
        return bookService.getBookByCountry(country);
    }

    @PutMapping("/update-book/{id}")
    public void updateBook(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
    }
}
