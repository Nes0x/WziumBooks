package me.nes0x.security;

import me.nes0x.author.Author;
import me.nes0x.author.AuthorService;
import me.nes0x.author.AuthorWriteModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
class AuthorValidator implements Validator {
    private final AuthorService authorService;

    AuthorValidator(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Author.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuthorWriteModel author = (AuthorWriteModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (author.getName().length() < 4 || author.getName().length() > 32) {
            errors.rejectValue("name", "Size.userForm.username");
        }
        if (authorService.getAuthorByName(author.getName()) != null) {
            errors.rejectValue("name", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (author.getPassword().length() < 8 || author.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
