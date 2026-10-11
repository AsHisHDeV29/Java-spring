package com.ashish.bookstore.controller;

import com.ashish.bookstore.model.Book;
import com.ashish.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/search")
    public List<Book> getBookByName(@RequestParam String bookname) {
        Optional<Book> book = bookRepository.findByBookName(bookname);

        if (book.isPresent()) {
            return List.of(book.get());
        }
        return List.of();
    }

    @PostMapping("/borrow-book")
    public String borrowBook(String bookName) {
        Optional<Book> BookName = bookRepository.findByBookName(bookName);

        if (BookName.isPresent()) {
            Book book = BookName.get();

            if (book.isInStock()) {
                book.setQuantityLeft(book.getQuantityLeft() - 1);

                bookRepository.save(book);

                return "Successfully borrowed: " + book.getBookName();
            } else {
                return "Sorry, " + book.getBookName() + " is out of stock";
            }
        }
        return "Book not found";
    }

}
