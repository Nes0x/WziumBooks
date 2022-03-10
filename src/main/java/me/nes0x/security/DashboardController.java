package me.nes0x.security;

import me.nes0x.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
class DashboardController {
    private final BookService bookService;

    DashboardController(final BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    String showAllBooks(Model model, Principal principal) {
        model.addAttribute("books", bookService.getBooksByAuthorName(principal.getName()));
        return "security/dashboard";
    }

}
