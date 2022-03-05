package me.nes0x.author;

import me.nes0x.comment.Comment;
import me.nes0x.comment.CommentReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    AuthorService(final AuthorRepository repository) {
        this.repository = repository;
    }

    public AuthorReadModel save(AuthorWriteModel author, LocalDateTime date) {
        author.setDate(date);
        Author result = repository.save(author.toAuthor());
        return new AuthorReadModel(result);
    }

    public List<AuthorReadModel> getAllAuthors() {
        return repository.findAll().stream()
                .map(AuthorReadModel::new)
                .collect(Collectors.toList());
    }

    public List<AuthorReadModel> getAllAuthorsOfName(String name) {
        return repository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(AuthorReadModel::new)
                .collect(Collectors.toList());
    }

    public AuthorReadModel getAuthorById(int id) {
        return repository.findById(id).map(AuthorReadModel::new).get();
    }
}
