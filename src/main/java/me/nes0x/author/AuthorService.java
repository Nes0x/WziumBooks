package me.nes0x.author;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthorService(final AuthorRepository repository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AuthorReadModel save(AuthorWriteModel author, LocalDateTime date) {
        author.setPassword(bCryptPasswordEncoder.encode(author.getPassword()));
        author.setDate(date);
        Author result = repository.save(author.toAuthor());
        return new AuthorReadModel(result);
    }

    public Author getAuthorByName(String name) {
        return repository.findByName(name);
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
