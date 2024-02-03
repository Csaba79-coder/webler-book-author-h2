package hu.webler.weblerbookauthorh2.controller;

import hu.webler.weblerbookauthorh2.service.AuthorService;
import hu.webler.weblerbookauthorh2.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookAndAuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookAndAuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
}
