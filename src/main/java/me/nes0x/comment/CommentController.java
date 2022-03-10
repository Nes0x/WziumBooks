package me.nes0x.comment;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/comments")
class CommentController {
    private final CommentService service;

    CommentController(final CommentService service) {
        this.service = service;
    }


    @PostMapping("/add/{id}")
    String createComment(@PathVariable int id, @ModelAttribute("comment") @Valid CommentWriteModel current, BindingResult bindingResult,
                         Principal principal) {

        if (bindingResult.hasErrors()) {
            return "redirect:/error";
        }


        service.save(current, id, principal.getName());
        return "redirect:/books/" + id;
    }

    @PostMapping("/remove/{id}")
    String removeComment(@PathVariable int id, Principal principal, @RequestParam(value = "bookId", required = false) String bookId) {
        if (service.checkComment(principal.getName(), id)) {
            service.delete(id);
        }
        return "redirect:/books/" + bookId;
    }

}
