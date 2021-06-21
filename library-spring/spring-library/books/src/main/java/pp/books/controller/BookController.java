package pp.books.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.books.domain.Book;
import pp.books.dto.OpinionDto;
import pp.books.service.BookService;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping("/books/classic-library")
    public ResponseEntity<List<Book>> getClassicLibraryBooks() {
        return new ResponseEntity<>(bookService.getClassicLibraryBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/rental-service")
    public ResponseEntity<List<Book>> getUserRentalServiceBooks() {
        return new ResponseEntity<>(bookService.getUserRentalServiceBooks(), HttpStatus.OK);
    }

    @DeleteMapping("/books/delete/{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable long bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("/books/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @PostMapping("/books/add-opinion")
    public ResponseEntity<?> addOpinion(@RequestBody OpinionDto opinionDto) {
        bookService.addOpinion(opinionDto);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping("/books/ratings")
    public ResponseEntity<List<Book>> getBooksOrderedByAvgRate() {
        return new ResponseEntity<>(bookService.getBooksOrderedByAvgRate(), HttpStatus.OK);
    }

    @GetMapping(value = "/save-test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String savePerformanceTest() {
        return bookService.savePerformanceTest();
    }

    @GetMapping(value = "/read-test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String readPerformanceTest() {
        return bookService.readPerformanceTest();
    }

}
