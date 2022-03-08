package me.nes0x.comment;

import me.nes0x.author.AuthorWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
class CommentController {
    private final CommentService service;

    CommentController(final CommentService service) {
        this.service = service;
    }


    @PostMapping("/add/{id}")
    String createComment(@PathVariable int id, @ModelAttribute("comment") @Valid CommentWriteModel current, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/error";
        }

        service.save(current, id);
        return "redirect:/books/" + id;
    }

}
