package hu.webler.weblerbookauthorh2.controller;

import hu.webler.weblerbookauthorh2.entity.Author;
import hu.webler.weblerbookauthorh2.entity.Book;
import hu.webler.weblerbookauthorh2.service.AuthorService;
import hu.webler.weblerbookauthorh2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookAndAuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookAndAuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/list-all")
    public String listAll(Model model) {
        List<Book> books = bookService.getAllBooks();
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        return "list-all";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "add-book-form";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/list-all";
    }

    @GetMapping("/add-author")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author-form";
    }

    @PostMapping("/save-author")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/list-all";
    }
}
