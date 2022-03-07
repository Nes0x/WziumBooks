package me.nes0x.book;

import me.nes0x.author.AuthorReadModel;
import me.nes0x.author.AuthorService;
import me.nes0x.comment.CommentService;
import me.nes0x.comment.CommentWriteModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
@RequestMapping("/books")
class BookController {
    private final BookService service;
    private final AuthorService authorService;
    private final CommentService commentService;

    BookController(final BookService service, final AuthorService authorService, final CommentService commentService) {
        this.service = service;
        this.authorService = authorService;
        this.commentService = commentService;
    }


    @GetMapping
    String showAllBooks(Model model, @RequestParam(value = "title", defaultValue = "") String title) {
        List<BookReadModel> books;

        if (title.equalsIgnoreCase("")) {
            books = service.getAllBooks();
            if (books.isEmpty()) {
                model.addAttribute("message", "Brak aktualnie dostępnych książek.");
            } else {
                model.addAttribute("books", books);
            }
        } else {
            books = service.getBooksByTitle(title);
            if (books.isEmpty()) {
                model.addAttribute("message", "Brak znalezionych książek o tytule " + title + ".");
            } else {
                model.addAttribute("books", service.getBooksByTitle(title));
            }
        }

        return "book/books";
    }

    @GetMapping("/{id}")
    String showBook(@PathVariable int id, Model model) {
        try {
            BookReadModel book = service.getBookById(id);
            model.addAttribute("comments", commentService.getAllCommentsOfIdBook(id));
            model.addAttribute("comment", new CommentWriteModel());
            model.addAttribute("book", book);
        } catch (NoSuchElementException exception) {
            model.addAttribute("message", "Książka z id " + id + " nie istnieje!");
            model.addAttribute("title", true);
        }

        return "book/book";
    }


    @GetMapping("/{id}/download")
    ResponseEntity<byte[]> downloadBook(@PathVariable int id) {
        BookReadModel book = service.getBookById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getTitle() + ".pdf\"")
                .body(book.getData());
    }



    @GetMapping("/add")
    String showAddBookForm(Model model) {
        model.addAttribute("book", new BookWriteModel());
        return "book/book_add";
    }

    @PostMapping("/add")
    String createBook(Model model, @ModelAttribute("book") @Valid BookWriteModel current, BindingResult bindingResult, @RequestParam(name = "addAuthor", defaultValue = "-1") String id, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message" ,"Wystąpił błąd!");
            try {
                AuthorReadModel author = authorService.getAuthorById(Integer.parseInt(id));
                model.addAttribute("author", author);
            } catch (NoSuchElementException | NumberFormatException ignored) {}
            return "book/book_add";
        }

        try {
            new URL(current.getImage());
        } catch (MalformedURLException exception) {
            model.addAttribute("message", "Nie podałeś linku do okładki!");
            return "book/book_add";
        }


        if (!file.isEmpty()) {
            if (!file.getOriginalFilename().endsWith(".pdf")) {
                model.addAttribute("message", "Możesz dodawać tylko pliki .pdf");
                return "book/book_add";
            }
        }



        service.save(current, Integer.parseInt(id), file);

        model.addAttribute("message","Stworzono książke!");
        model.addAttribute("book", new BookWriteModel());
        return "book/book_add";
    }


    @PostMapping(path = "/add", params = "deleteAuthor")
    String deleteAuthor(Model model, @ModelAttribute("book") BookWriteModel current) {
        model.addAttribute("author",null);
        return "book/book_add";
    }

    @PostMapping(path = "/add", params = "searchAuthor")
    String searchAuthor(Model model, @ModelAttribute("book") BookWriteModel current,  @RequestParam(name = "name", defaultValue = "") String name) {
        model.addAttribute("authors", authorService.getAllAuthorsOfName(name));
        return "book/book_add";
    }

    @PostMapping(path = "/add", params = "addAuthor")
    String addAuthor(Model model, @ModelAttribute("book") BookWriteModel current,
                     @RequestParam(name = "addAuthor", defaultValue = "-1") String id) {
        try {
            AuthorReadModel author = authorService.getAuthorById(Integer.parseInt(id));
            model.addAttribute("author", author);
            model.addAttribute("message", "Wybrałeś autora o id " + id);
        } catch (NoSuchElementException | NumberFormatException exception) {
            model.addAttribute("message", "Autor z " + id + " nie istnieje!");
        }
        return "book/book_add";
    }


    @ModelAttribute("authors")
    List<AuthorReadModel> getAuthors() {
        return authorService.getAllAuthors();
    }



}
