package me.nes0x.book;

import me.nes0x.author.Author;
import me.nes0x.author.AuthorReadModel;
import me.nes0x.author.AuthorService;
import me.nes0x.comment.CommentReadModel;
import me.nes0x.comment.CommentService;
import me.nes0x.comment.CommentWriteModel;
import me.nes0x.security.SecurityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/books")
class BookController {
    private final BookService service;
    private final AuthorService authorService;
    private final CommentService commentService;
    private final BookRepository bookRepository;
    private final SecurityService securityService;

    BookController(final BookService service, final AuthorService authorService, final CommentService commentService, final BookRepository bookRepository, final SecurityService securityService) {
        this.service = service;
        this.authorService = authorService;
        this.commentService = commentService;
        this.bookRepository = bookRepository;
        this.securityService = securityService;
    }

    @ModelAttribute("authors")
    List<AuthorReadModel> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping
    String showAllBooks(Model model, @RequestParam(value = "title", defaultValue = "") String title) {
        List<BookReadModel> books;

        if (title.equalsIgnoreCase("")) {
            books = service.getAllBooks();
        } else {
            books = service.getBooksByTitle(title);
        }

        if (books.isEmpty()) {
            model.addAttribute("message", "Brak aktualnie dostępnych książek.");
        } else {
            model.addAttribute("books", books);
        }

        return "book/books";
    }

    @GetMapping("/{id}")
    String showBook(@PathVariable int id, Model model) {
        try {
            BookReadModel book = service.getBookById(id);
            List<CommentReadModel> comments = commentService.getAllCommentsOfIdBook(id);
            model.addAttribute("comments", comments);
            model.addAttribute("comment", new CommentWriteModel());
            model.addAttribute("book", book);
            float stars = 0f;

            for (CommentReadModel comment : comments) {
                stars += comment.getStars();
            }

            model.addAttribute("stars", Math.round(stars/comments.size()));
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
    String createBook(Model model, @ModelAttribute("book") @Valid BookWriteModel current,
                      BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file,
                      Principal principal) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message" ,"Wystąpił błąd!");
            return "book/book_add";
        }

        try {
            new URL(current.getImage());
        } catch (MalformedURLException exception) {
            model.addAttribute("message", "Nie podałeś linku do okładki!");
            return "book/book_add";
        }


        if (!file.isEmpty()) {
            if (!file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)) {
                model.addAttribute("message", "Możesz dodawać tylko pliki .pdf");
                return "book/book_add";
            }
        }



        service.save(current, file, principal.getName());

        model.addAttribute("message","Stworzono książke!");
        model.addAttribute("book", new BookWriteModel());
        return "book/book_add";
    }

    @PostMapping(path = "/{id}",  params = "deleteBook")
    @Transactional
    public String deleteBook(@PathVariable int id, Principal principal, Model model) {

        if (!securityService.isAuthenticated()) {
            return "redirect:/";
        }

        boolean isDeleted = service.deleteBook(id, principal.getName());

        if (isDeleted) {
            model.addAttribute("message", "Pomyślnie usunałeś swoją ksiażke!");
        } else {
            model.addAttribute("message", "Coś poszło nie tak!");
        }

        model.addAttribute("books", service.getBooksByAuthorName(principal.getName()));
        return "security/dashboard";
    }


}
