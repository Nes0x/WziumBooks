package me.nes0x.author;


import me.nes0x.book.BookReadModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/authors")
class AuthorController {
    private final AuthorService service;

    AuthorController(final AuthorService service) {
        this.service = service;
    }

    @GetMapping("/add")
    String showAddAuthorForm(Model model) {
        model.addAttribute("author", new AuthorWriteModel());
        return "author/author_add";
    }

    @GetMapping("/{id}")
    String showBook(@PathVariable int id, Model model) {
        try {
            AuthorReadModel author = service.getAuthorById(id);
            model.addAttribute("author", author);
        } catch (NoSuchElementException exception) {
            model.addAttribute("message", "Autor z id " + id + " nie istnieje!");
            model.addAttribute("title", true);
        }

        return "author/author";
    }

    @PostMapping("/add")
    String createBook(Model model, @ModelAttribute("author") @Valid AuthorWriteModel current, BindingResult bindingResult,
                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message" ,"Wystąpił błąd!");
            return "author/author_add";
        }

        service.save(current, date);

        model.addAttribute("message","Stworzono autora!");
        model.addAttribute("author", new AuthorWriteModel());
        return "author/author_add";
    }

}
