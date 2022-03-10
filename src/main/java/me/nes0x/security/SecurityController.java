package me.nes0x.security;


import me.nes0x.author.AuthorService;
import me.nes0x.author.AuthorWriteModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
class SecurityController {
    private final AuthorService authorService;
    private final SecurityService securityService;
    private final AuthorValidator authorValidator;

    SecurityController(final AuthorService authorService, final SecurityService securityService, final AuthorValidator authorValidator) {
        this.authorService = authorService;
        this.securityService = securityService;
        this.authorValidator = authorValidator;
    }


    @GetMapping("/registration")
    String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("author", new AuthorWriteModel());

        return "registration";
    }

    @PostMapping("/registration")
    String registration(@ModelAttribute("author") AuthorWriteModel current, BindingResult bindingResult, Model model,
                        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date) {
        authorValidator.validate(current, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        authorService.save(current, date);
        model.addAttribute("message", "Pomyślnie się zarejestrowałeś!");
        return "registration";
    }

    @GetMapping("/login")
    String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Login lub hasło jest nieprawidłowe.");

        if (logout != null)
            model.addAttribute("message", "Zostałeś wylogowany.");

        return "login";
    }
}
